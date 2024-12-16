package api.reservas.api.gateway;

import api.reservas.api.domain.enums.NotaAvaliacao;
import api.reservas.api.domain.paging.PagedResult;
import api.reservas.api.domain.paging.PagingInfo;
import api.reservas.api.usecase.dto.AvaliacaoComIdDTO;

public interface AvaliacaoGateway {

    void fazerAvaliacao(Long idReserva, NotaAvaliacao notaAvaliacao, String comentario);
    void excluirAvaliacao(Long idAvaliacao);
    PagedResult<AvaliacaoComIdDTO> listarPorCnpj(String cnpjRestaurante, PagingInfo pagingInfo);

}
