package api.reservas.api.gateway;

import api.reservas.api.domain.avaliacoes.Avaliacao;
import api.reservas.api.domain.enums.NotaAvaliacao;
import api.reservas.api.domain.paging.PagedResult;
import api.reservas.api.domain.paging.PagingInfo;

public interface AvaliacaoGateway {

    void fazerAvaliacao(Long idReserva, NotaAvaliacao notaAvaliacao, String comentario);
    void excluirAvaliacao(Long idAvaliacao);
    PagedResult<Avaliacao> listarPorCnpj(String cnpjRestaurante, PagingInfo pagingInfo);

}
