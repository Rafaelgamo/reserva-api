package api.reservas.api.gateway.database.jpa;

import api.reservas.api.domain.enums.NotaAvaliacao;
import api.reservas.api.domain.paging.PagedResult;
import api.reservas.api.domain.paging.PagingInfo;
import api.reservas.api.exception.RecursoNaoEncontradoException;
import api.reservas.api.gateway.AvaliacaoGateway;
import api.reservas.api.gateway.database.jpa.entity.AvaliacaoEntity;
import api.reservas.api.gateway.database.jpa.entity.ReservaEntity;
import api.reservas.api.gateway.database.jpa.repository.AvaliacaoRepository;
import api.reservas.api.usecase.dto.AvaliacaoComIdDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoJpaGateway implements AvaliacaoGateway {

    private static final Logger logger = LoggerFactory.getLogger(AvaliacaoJpaGateway.class);

    private final AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoJpaGateway(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    @Override
    public void fazerAvaliacao(Long idReserva, NotaAvaliacao notaAvaliacao, String comentario) {
        var reservaEntity = new ReservaEntity();
        reservaEntity.setId(idReserva);

        var novaAvaliacao = new AvaliacaoEntity();
        novaAvaliacao.setReserva(reservaEntity);
        novaAvaliacao.setNotaAvaliacao(notaAvaliacao);
        novaAvaliacao.setComentario(comentario);

        avaliacaoRepository.save(novaAvaliacao);
    }

    @Override
    public void excluirAvaliacao(Long idAvaliacao) {
        var avaliacaoEntity = avaliacaoRepository.findById(idAvaliacao);
        if (avaliacaoEntity.isEmpty()) {
            logger.debug("Avaliação não encontrada para exclusão: {}", idAvaliacao);
            throw new RecursoNaoEncontradoException(AvaliacaoEntity.class, "id", idAvaliacao);
        }

        avaliacaoRepository.delete(avaliacaoEntity.get());
    }

    @Override
    public PagedResult<AvaliacaoComIdDTO> listarPorCnpj(String cnpjRestaurante, PagingInfo pagingInfo) {
        var entityPage = avaliacaoRepository.buscarPorCnpj(cnpjRestaurante, pagingInfo.toPageRequest());
        return PagedResult.of(entityPage.map(this::mapToDTO), pagingInfo);
    }

    private AvaliacaoComIdDTO mapToDTO(AvaliacaoEntity avaliacaoEntity) {
        return new AvaliacaoComIdDTO(
                avaliacaoEntity.getId(),
                avaliacaoEntity.getReserva().getId(),
                avaliacaoEntity.getNotaAvaliacao(),
                avaliacaoEntity.getComentario()
        );
    }
}
