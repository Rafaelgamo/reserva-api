package api.reservas.api.usecase.avaliacoes;

import api.reservas.api.domain.enums.NotaAvaliacao;
import api.reservas.api.exception.RecursoNaoEncontradoException;
import api.reservas.api.gateway.AvaliacaoGateway;
import api.reservas.api.gateway.ReservaGateway;
import api.reservas.api.gateway.database.jpa.entity.ReservaEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AvaliarReservaUseCase {

    private static final Logger logger = LoggerFactory.getLogger(AvaliarReservaUseCase.class);

    private final AvaliacaoGateway avaliacaoGateway;

    private final ReservaGateway reservaGateway;

    public AvaliarReservaUseCase(AvaliacaoGateway avaliacaoGateway, ReservaGateway reservaGateway) {
        this.avaliacaoGateway = avaliacaoGateway;
        this.reservaGateway = reservaGateway;
    }

    public void fazerAvaliacao(Long idReserva, NotaAvaliacao notaAvaliacao, String comentario) {
        var reserva = reservaGateway.buscarPorId(idReserva);
        if (reserva == null) {
            logger.debug("Reserva não encontrada para avaliação: {}", idReserva);
            throw new RecursoNaoEncontradoException(ReservaEntity.class, "id", idReserva);
        }
        avaliacaoGateway.fazerAvaliacao(idReserva, notaAvaliacao, comentario);
    }
}
