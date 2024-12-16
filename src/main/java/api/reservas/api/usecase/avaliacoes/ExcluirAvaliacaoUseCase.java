package api.reservas.api.usecase.avaliacoes;

import api.reservas.api.gateway.AvaliacaoGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExcluirAvaliacaoUseCase {

    private static final Logger log = LoggerFactory.getLogger(ExcluirAvaliacaoUseCase.class);

    private final AvaliacaoGateway avaliacaoGateway;

    public ExcluirAvaliacaoUseCase(AvaliacaoGateway avaliacaoGateway) {
        this.avaliacaoGateway = avaliacaoGateway;
    }

    @Transactional
    public void excluirAvaliacao(Long idAvaliacao) {
        avaliacaoGateway.excluirAvaliacao(idAvaliacao);
    }
}
