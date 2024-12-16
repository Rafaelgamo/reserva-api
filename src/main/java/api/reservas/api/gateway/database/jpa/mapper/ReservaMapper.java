package api.reservas.api.gateway.database.jpa.mapper;

import api.reservas.api.domain.reserva.Reserva;
import api.reservas.api.gateway.database.jpa.entity.ReservaEntity;
import api.reservas.api.gateway.database.jpa.entity.RestauranteEntity;

public class ReservaMapper {
    public ReservaEntity mapToEntity(Reserva reserva, RestauranteEntity restaurante) {
        var entity = new ReservaEntity();
        entity.setRestaurante(restaurante);
        entity.setData(reserva.data());
        entity.setStatus(reserva.statusReserva());
        return entity;
    }
}
