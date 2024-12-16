package api.reservas.api.gateway;

import api.reservas.api.domain.paging.PagedResult;
import api.reservas.api.domain.paging.PagingInfo;
import api.reservas.api.domain.reserva.Reserva;
import api.reservas.api.usecase.dto.ReservaComIdDTO;

public interface ReservaGateway {
    void cancelarReserva(Long idReserva);

    void concluirReserva(Long idReserva);

    Integer contarReservasNaoConcluidas(String cnpj);

    Long cadastrarReserva(String cnpj, Reserva reserva);

    PagedResult<ReservaComIdDTO> buscarPorCnpj(String cnpj, PagingInfo pagingInfo);

    Reserva buscarPorId(Long idReserva);
}
