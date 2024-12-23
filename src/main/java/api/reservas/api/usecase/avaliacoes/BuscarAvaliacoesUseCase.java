package api.reservas.api.usecase.avaliacoes;

import api.reservas.api.domain.paging.PagedResult;
import api.reservas.api.domain.paging.PagingInfo;
import api.reservas.api.gateway.AvaliacaoGateway;
import api.reservas.api.usecase.dto.AvaliacaoComIdDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BuscarAvaliacoesUseCase {

    private static final Logger logger = LoggerFactory.getLogger(BuscarAvaliacoesUseCase.class);

    private final AvaliacaoGateway avaliacaoGateway;

    public BuscarAvaliacoesUseCase(AvaliacaoGateway avaliacaoGateway) {
        this.avaliacaoGateway = avaliacaoGateway;
    }

    public PagedResult<AvaliacaoComIdDTO> buscarPorCnpj(String cnpj, PagingInfo pagingInfo) {
        return avaliacaoGateway.listarPorCnpj(cnpj, pagingInfo);
    }

}
