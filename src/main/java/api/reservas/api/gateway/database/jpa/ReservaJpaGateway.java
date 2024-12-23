package api.reservas.api.gateway.database.jpa;

import api.reservas.api.domain.enums.StatusReserva;
import api.reservas.api.domain.paging.PagedResult;
import api.reservas.api.domain.paging.PagingInfo;
import api.reservas.api.domain.reserva.Reserva;
import api.reservas.api.gateway.ReservaGateway;
import api.reservas.api.gateway.database.jpa.entity.ReservaEntity;
import api.reservas.api.gateway.database.jpa.mapper.ReservaMapper;
import api.reservas.api.gateway.database.jpa.repository.ReservaRepository;
import api.reservas.api.gateway.database.jpa.repository.RestauranteRepository;
import api.reservas.api.usecase.dto.ReservaComIdDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
        var reservaOpt = reservaRepository.findById(idReserva);
        if (reservaOpt.isEmpty()) {
            logger.warn("Reserva não encontrada ao concluir por id: {}", idReserva);
            return;
        }

        var reservaEntity = reservaOpt.get();
        reservaEntity.setStatus(StatusReserva.CONCLUIDA);

        reservaRepository.save(reservaEntity);
    }

    @Override
    public Integer contarReservasNaoConcluidas(String cnpj) {
        return reservaRepository.contarReservasNaoConcluidas(cnpj);
    }

    @Override
    public Long cadastrarReserva(String cnpj, Reserva reserva) {
        var restauranteEntity = restauranteRepository.findByCnpj(cnpj);
        var reservaEntity = reservaMapper.mapToEntity(reserva, restauranteEntity.get());
        return reservaRepository.save(reservaEntity).getId();
    }

    @Override
    public PagedResult<ReservaComIdDTO> buscarPorCnpj(String cnpj, PagingInfo pagingInfo) {
        var pageReservas = reservaRepository.buscarPorCnpj(cnpj, pagingInfo.toPageRequest());
        return PagedResult.of(pageReservas.map(ReservaComIdDTO::new), pagingInfo);
    }

    @Override
    public Reserva buscarPorId(Long idReserva) {
        Optional<ReservaEntity> optReserva = reservaRepository.findById(idReserva);
        if (optReserva.isEmpty()) {
            return null;
        }

        return reservaMapper.mapToDomain(optReserva.get());
    }
}
