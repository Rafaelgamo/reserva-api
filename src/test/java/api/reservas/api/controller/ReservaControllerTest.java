package api.reservas.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import api.reservas.api.controller.json.IdJson;
import api.reservas.api.controller.json.ReservaJson;
import api.reservas.api.domain.paging.PagedResult;
import api.reservas.api.domain.paging.PagingInfo;
import api.reservas.api.usecase.dto.ReservaComIdDTO;
import api.reservas.api.usecase.dto.ReservaDTO;
import api.reservas.api.usecase.reservas.AlterarReservaUseCase;
import api.reservas.api.usecase.reservas.BuscarReservasUseCase;
import api.reservas.api.usecase.reservas.FazerReservaUseCase;

@ExtendWith(MockitoExtension.class)
class ReservaControllerTest {

	@Mock
	private FazerReservaUseCase fazerReservaUseCase;

	@Mock
	private BuscarReservasUseCase buscarReservasUseCase;

	@Mock
	private AlterarReservaUseCase alterarReservaUseCase;

	@InjectMocks
	private ReservaController reservaController;

	@Test
	void testFazerReserva() {
		ReservaJson reservaJson = new ReservaJson("12345678901234", LocalDateTime.of(2024, 12, 25, 19, 0));
		ReservaDTO reservaDTO = new ReservaDTO(reservaJson.cnpjRestaurante(), reservaJson.data());
		Long expectedId = 1L;

		when(fazerReservaUseCase.fazerReserva(reservaDTO)).thenReturn(expectedId);

		ResponseEntity<IdJson> response = reservaController.fazerReserva(reservaJson);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(expectedId, response.getBody().id());
		verify(fazerReservaUseCase, times(1)).fazerReserva(reservaDTO);
	}

	@Test
	void testListarPorRestaurante() {
		String cnpj = "12345678901234";
		Integer page = 1;
		Integer pageSize = 10;
		PagingInfo pagingInfo = PagingInfo.of(page, pageSize);

		PagedResult<ReservaComIdDTO> expectedPagedResult = PagedResult.of(Collections.emptyList());
		when(buscarReservasUseCase.buscarPorCnpj(cnpj, pagingInfo)).thenReturn(expectedPagedResult);

		ResponseEntity<PagedResult<ReservaComIdDTO>> response = reservaController.listarPorRestaurante(cnpj, page,
				pageSize);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedPagedResult, response.getBody());
		verify(buscarReservasUseCase, times(1)).buscarPorCnpj(cnpj, pagingInfo);
	}

	@Test
	void testConcluirReserva() {
		Long idReserva = 1L;

		ResponseEntity<Void> response = reservaController.concluirReserva(idReserva);

		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		verify(alterarReservaUseCase, times(1)).concluirReserva(idReserva);
	}

	@Test
	void testCancelarReserva() {
		Long idReserva = 1L;

		ResponseEntity<Void> response = reservaController.cancelarReserva(idReserva);

		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		verify(alterarReservaUseCase, times(1)).cancelarReserva(idReserva);
	}
}
