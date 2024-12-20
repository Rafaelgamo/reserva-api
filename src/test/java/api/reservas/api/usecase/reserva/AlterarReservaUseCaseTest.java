package api.reservas.api.usecase.reserva;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import api.reservas.api.exception.ValidacaoException;
import api.reservas.api.gateway.ReservaGateway;
import api.reservas.api.usecase.reservas.AlterarReservaUseCase;

@ExtendWith(MockitoExtension.class)
class AlterarReservaUseCaseTest {

	@Mock
	private ReservaGateway reservaGateway;

	@InjectMocks
	private AlterarReservaUseCase alterarReservaUseCase;

	@Test
	void testCancelarReservaComIdValido() {
		Long idReserva = 1L;

		alterarReservaUseCase.cancelarReserva(idReserva);

		verify(reservaGateway, times(1)).cancelarReserva(idReserva);
	}

	@Test
	void testCancelarReservaComIdInvalido() {
		Long idReserva = -1L;

		assertThrows(ValidacaoException.class, () -> alterarReservaUseCase.cancelarReserva(idReserva));

		verify(reservaGateway, never()).cancelarReserva(any());
	}

	@Test
	void testConcluirReservaComIdValido() {
		Long idReserva = 1L;

		alterarReservaUseCase.concluirReserva(idReserva);

		verify(reservaGateway, times(1)).concluirReserva(idReserva);
	}

	@Test
	void testConcluirReservaComIdInvalido() {
		Long idReserva = null;

		assertThrows(ValidacaoException.class, () -> alterarReservaUseCase.concluirReserva(idReserva));

		verify(reservaGateway, never()).concluirReserva(any());
	}
}
