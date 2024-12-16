package api.reservas.api.controller.json;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record ReservaJson(
        @NotBlank @Size(min = 11, max = 14) String cnpjRestaurante,
        @NotNull @DateTimeFormat LocalDateTime data
) {
}
