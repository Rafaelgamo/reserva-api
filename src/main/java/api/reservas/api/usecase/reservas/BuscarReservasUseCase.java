package api.reservas.api.usecase.reservas;

import api.reservas.api.domain.paging.PagedResult;
import api.reservas.api.domain.paging.PagingInfo;
import api.reservas.api.gateway.ReservaGateway;
import api.reservas.api.usecase.dto.ReservaComIdDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BuscarReservasUseCase {
    private static final Logger logger = LoggerFactory.getLogger(BuscarReservasUseCase.class);

    private final ReservaGateway reservaGateway;

    public BuscarReservasUseCase(ReservaGateway reservaGateway) {
        this.reservaGateway = reservaGateway;
    }

    public PagedResult<ReservaComIdDTO> buscarPorCnpj(String cnpj, PagingInfo pagingInfo) {
        return reservaGateway.buscarPorCnpj(cnpj, pagingInfo);
    }
}
