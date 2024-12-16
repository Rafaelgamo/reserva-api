package api.reservas.api.gateway;

import api.reservas.api.controller.json.ReservaComIdDTO;
import api.reservas.api.domain.paging.PagedResult;
import api.reservas.api.domain.paging.PagingInfo;
import api.reservas.api.domain.reserva.Reserva;

import java.time.LocalDateTime;

public interface ReservaGateway {
    void cancelarReserva(Long idReserva);

    void concluirReserva(Long idReserva);

    Integer contarReservasPorDia(String cnpj, LocalDateTime dataRequisitada);

    Long cadastrarReserva(String cnpj, Reserva reserva);

    PagedResult<ReservaComIdDTO> buscarPorCnpj(String cnpj, PagingInfo pagingInfo);
}
