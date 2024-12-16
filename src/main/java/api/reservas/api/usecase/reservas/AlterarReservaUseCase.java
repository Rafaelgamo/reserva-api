package api.reservas.api.usecase.reservas;

import api.reservas.api.exception.ValidacaoException;
import api.reservas.api.gateway.ReservaGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AlterarReservaUseCase {

    private static final Logger logger = LoggerFactory.getLogger(AlterarReservaUseCase.class);

    private final ReservaGateway reservaGateway;

    public AlterarReservaUseCase(ReservaGateway reservaGateway) {
        this.reservaGateway = reservaGateway;
    }

    public void cancelarReserva(Long idReserva) {
        if (idReserva == null || idReserva < 1) {
            logger.warn("ID da reserva inválido: id={}", idReserva);
            throw new ValidacaoException("ID da reserva inválido");
        }

        reservaGateway.cancelarReserva(idReserva);
    }

    public void concluirReserva(Long idReserva) {
        if (idReserva == null || idReserva < 1) {
            logger.warn("ID da reserva inválido: id={}", idReserva);
            throw new ValidacaoException("ID da reserva inválido");
        }

        reservaGateway.concluirReserva(idReserva);
    }
}
