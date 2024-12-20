package api.reservas.api.usecase.reserva;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import api.reservas.api.domain.paging.PagedResult;
import api.reservas.api.domain.paging.PagingInfo;
import api.reservas.api.gateway.ReservaGateway;
import api.reservas.api.usecase.dto.ReservaComIdDTO;
import api.reservas.api.usecase.reservas.BuscarReservasUseCase;

@ExtendWith(MockitoExtension.class)
class BuscarReservasUseCaseTest {

	@Mock
	private ReservaGateway reservaGateway;

	@InjectMocks
	private BuscarReservasUseCase buscarReservasUseCase;

	@Test
	void testBuscarPorCnpj() {
		String cnpj = "12345678901234";
		PagingInfo pagingInfo = PagingInfo.of(1, 10);
		PagedResult<ReservaComIdDTO> pagedResult = PagedResult.of(java.util.Collections.emptyList());

		when(reservaGateway.buscarPorCnpj(cnpj, pagingInfo)).thenReturn(pagedResult);

		PagedResult<ReservaComIdDTO> result = buscarReservasUseCase.buscarPorCnpj(cnpj, pagingInfo);

		assertNotNull(result);
		verify(reservaGateway, times(1)).buscarPorCnpj(cnpj, pagingInfo);
	}
}
