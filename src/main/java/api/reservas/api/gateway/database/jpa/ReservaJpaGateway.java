package api.reservas.api.gateway.database.jpa;

import api.reservas.api.domain.paging.PagedResult;
import api.reservas.api.domain.paging.PagingInfo;
import api.reservas.api.domain.reserva.Reserva;
import api.reservas.api.domain.reserva.StatusReserva;
import api.reservas.api.gateway.ReservaGateway;
import api.reservas.api.gateway.database.jpa.mapper.ReservaMapper;
import api.reservas.api.gateway.database.jpa.repository.ReservaRepository;
import api.reservas.api.gateway.database.jpa.repository.RestauranteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReservaJpaGateway implements ReservaGateway {

    private static final Logger logger = LoggerFactory.getLogger(ReservaJpaGateway.class);

    private final ReservaRepository reservaRepository;
    private final RestauranteRepository restauranteRepository;

    private final ReservaMapper reservaMapper = new ReservaMapper();

    public ReservaJpaGateway(ReservaRepository reservaRepository, RestauranteRepository restauranteRepository) {
        this.reservaRepository = reservaRepository;
        this.restauranteRepository = restauranteRepository;
    }

    @Override
    public void cancelarReserva(Long idReserva) {
        var reserva = reservaRepository.findById(idReserva);
        if (reserva.isEmpty()) {
            logger.warn("Reserva não encontrada ao cancelar por id: {}", idReserva);
            return;
        }

        reserva.get().setStatus(StatusReserva.CANCELADA);
    }

    @Override
    public void concluirReserva(Long idReserva) {
        var reserva = reservaRepository.findById(idReserva);
        if (reserva.isEmpty()) {
            logger.warn("Reserva não encontrada ao concluir por id: {}", idReserva);
            return;
        }

        reserva.get().setStatus(StatusReserva.CONCLUIDA);
    }

    @Override
    public Integer contarReservasPorDia(String cnpj, LocalDateTime dataRequisitada) {
        return reservaRepository.contarReservasPorDia(cnpj, dataRequisitada);
    }

    @Override
    public Long cadastrarReserva(String cnpj, Reserva reserva) {
        var restauranteEntity = restauranteRepository.findByCnpj(cnpj);
        var reservaEntity = reservaMapper.mapToEntity(reserva, restauranteEntity.get());
        return reservaRepository.save(reservaEntity).getId();
    }

    @Override
    public PagedResult<Reserva> buscarPorCnpj(String cnpj, PagingInfo pagingInfo) {
        Page<Reserva> pageReservas = reservaRepository.buscarPorCnpj(cnpj, pagingInfo.toPageRequest());
        return PagedResult.of(pageReservas, pagingInfo);
    }
}
