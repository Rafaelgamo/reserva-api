package api.reservas.api.usecase.reserva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import api.reservas.api.domain.reserva.Reserva;
import api.reservas.api.domain.restaurante.Restaurante;
import api.reservas.api.exception.RecursoNaoEncontradoException;
import api.reservas.api.exception.ValidacaoException;
import api.reservas.api.gateway.ReservaGateway;
import api.reservas.api.gateway.RestauranteGateway;
import api.reservas.api.usecase.dto.ReservaDTO;
import api.reservas.api.usecase.reservas.FazerReservaUseCase;

@ExtendWith(MockitoExtension.class)
class FazerReservaUseCaseTest {

	@Mock
	private ReservaGateway reservaGateway;

	@Mock
	private RestauranteGateway restauranteGateway;

	@InjectMocks
	private FazerReservaUseCase fazerReservaUseCase;

	@Test
	void testFazerReservaComSucesso() {
		String cnpj = "12345678901234";
		LocalDateTime dataRequisitada = LocalDateTime.now().plusDays(1);
		ReservaDTO reservaDTO = new ReservaDTO(cnpj, dataRequisitada);

		Restaurante restaurante = mock(Restaurante.class);
		when(restauranteGateway.buscarPorCnpj(any())).thenReturn(restaurante);
		when(restaurante.horaReservaValida(anyInt())).thenReturn(true);
		when(restaurante.capacidadeEmMesas()).thenReturn(10);

		when(reservaGateway.contarReservasNaoConcluidas(cnpj)).thenReturn(5);
		when(reservaGateway.cadastrarReserva(any(), any())).thenReturn(1L);

		Long idReserva = fazerReservaUseCase.fazerReserva(reservaDTO);

		assertEquals(1L, idReserva);
		verify(reservaGateway, times(1)).cadastrarReserva(eq(cnpj), any(Reserva.class));
	}

	@Test
	void testFazerReservaComDataNoPassado() {
		String cnpj = "12345678901234";
		LocalDateTime dataRequisitada = LocalDateTime.now().minusDays(1);
		ReservaDTO reservaDTO = new ReservaDTO(cnpj, dataRequisitada);

		assertThrows(ValidacaoException.class, () -> fazerReservaUseCase.fazerReserva(reservaDTO));
		verifyNoInteractions(reservaGateway);
		verifyNoInteractions(restauranteGateway);
	}

	@Test
	void testFazerReservaComRestauranteNaoEncontrado() {
		String cnpj = "12345678901234";
		LocalDateTime dataRequisitada = LocalDateTime.now().plusDays(1);
		ReservaDTO reservaDTO = new ReservaDTO(cnpj, dataRequisitada);

		when(restauranteGateway.buscarPorCnpj(any())).thenReturn(null);

		assertThrows(RecursoNaoEncontradoException.class, () -> fazerReservaUseCase.fazerReserva(reservaDTO));
		verify(restauranteGateway, times(1)).buscarPorCnpj(cnpj);
		verifyNoInteractions(reservaGateway);
	}

	@Test
	void testFazerReservaSemMesasDisponiveis() {
		String cnpj = "12345678901234";
		LocalDateTime dataRequisitada = LocalDateTime.now().plusDays(1);
		ReservaDTO reservaDTO = new ReservaDTO(cnpj, dataRequisitada);

		Restaurante restaurante = mock(Restaurante.class);
		when(restauranteGateway.buscarPorCnpj(any())).thenReturn(restaurante);
		when(restaurante.horaReservaValida(anyInt())).thenReturn(true);
		when(restaurante.capacidadeEmMesas()).thenReturn(10);
		when(reservaGateway.contarReservasNaoConcluidas(cnpj)).thenReturn(10);

		assertThrows(ValidacaoException.class, () -> fazerReservaUseCase.fazerReserva(reservaDTO));
		verify(restauranteGateway, times(1)).buscarPorCnpj(cnpj);
		verify(reservaGateway, times(1)).contarReservasNaoConcluidas(cnpj);
	}

	@Test
	void testFazerReservaComRestauranteFechado() {
		String cnpj = "12345678901234";
		LocalDateTime dataRequisitada = LocalDateTime.now().plusDays(1);
		ReservaDTO reservaDTO = new ReservaDTO(cnpj, dataRequisitada);

		Restaurante restaurante = mock(Restaurante.class);
		when(restauranteGateway.buscarPorCnpj(any())).thenReturn(restaurante);
		when(restaurante.horaReservaValida(anyInt())).thenReturn(false);

		assertThrows(ValidacaoException.class, () -> fazerReservaUseCase.fazerReserva(reservaDTO));
		verify(restauranteGateway, times(1)).buscarPorCnpj(cnpj);
		verifyNoInteractions(reservaGateway);
	}
}
