package api.reservas.api.usecase.dto;

import java.time.LocalDateTime;

public record ReservaDTO(
        String cnpjRestaurante,
        LocalDateTime data
) {
}
