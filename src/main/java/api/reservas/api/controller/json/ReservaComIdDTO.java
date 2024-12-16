package api.reservas.api.controller.json;

import api.reservas.api.domain.enums.StatusReserva;
import api.reservas.api.gateway.database.jpa.entity.ReservaEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record ReservaComIdDTO(
        @NotNull @Positive Long id,
        String cnpjRestaurante,
        LocalDateTime data,
        StatusReserva statusReserva
) {

    public ReservaComIdDTO(ReservaEntity reservaEntity) {
        this(
                reservaEntity.getId(),
                reservaEntity.getRestaurante().getCnpj(),
                reservaEntity.getData(),
                reservaEntity.getStatus()
        );
    }
}
