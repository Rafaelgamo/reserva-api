package api.reservas.api.usecase.reservas;

import api.reservas.api.domain.enums.StatusReserva;
import api.reservas.api.domain.reserva.Reserva;
import api.reservas.api.domain.restaurante.Restaurante;
import api.reservas.api.exception.RecursoNaoEncontradoException;
import api.reservas.api.exception.ValidacaoException;
import api.reservas.api.gateway.ReservaGateway;
import api.reservas.api.gateway.RestauranteGateway;
import api.reservas.api.usecase.dto.ReservaDTO;
import api.reservas.api.util.ValidadorFormatoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FazerReservaUseCase {
    private static final Logger logger = LoggerFactory.getLogger(FazerReservaUseCase.class);

    private final ReservaGateway reservaGateway;
    private final RestauranteGateway restauranteGateway;

    public FazerReservaUseCase(ReservaGateway reservaGateway, RestauranteGateway restauranteGateway) {
        this.reservaGateway = reservaGateway;
        this.restauranteGateway = restauranteGateway;
    }

    public Long fazerReserva(ReservaDTO reservaDTO) {
        logger.info("Fazendo reserva: {}", reservaDTO);

        var dataRequisitada = reservaDTO.data();
        var horaRequisitada = dataRequisitada.getHour();

        if (dataRequisitada.isBefore(LocalDateTime.now())) {
            logger.debug("Reservas têm que ser para momentos futuros: {}", dataRequisitada);
            throw new ValidacaoException("Reservas têm que ser para momentos futuros.");
        }

        var cnpj = ValidadorFormatoUtil.somenteDigitos(reservaDTO.cnpjRestaurante());
        var restaurante = restauranteGateway.buscarPorCnpj(cnpj);

        if (restaurante == null) {
            logger.debug("Não encontrado restaurante ao tentar fazer reserva: {}", cnpj);
            throw new RecursoNaoEncontradoException(Restaurante.class, "cnpj", cnpj);
        }

        if (!restaurante.horaReservaValida(horaRequisitada)) {
            logger.debug("No horário requisitado o restaurante estará fechado: {}.", dataRequisitada);
            throw new ValidacaoException("No horário requisitado o restaurante estará fechado.");
        }

        var qtdReservasEmAberto = reservaGateway.contarReservasNaoConcluidas(cnpj);
        if (qtdReservasEmAberto >= restaurante.capacidadeEmMesas()) {
            logger.debug("Sem mesas disponíveis no restaurante: {}.", cnpj);
            throw new ValidacaoException("Sem mesas disponíveis.");
        }

        var reserva = new Reserva(
                cnpj,
                dataRequisitada,
                StatusReserva.AGENDADA
        );

        return reservaGateway.cadastrarReserva(restaurante.cnpj(), reserva);
    }
}
