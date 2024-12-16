package api.reservas.api.controller.json;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public record AvaliacaoJson(
        @NotNull @Range(min = 1, max = 5) Integer notaAvaliacao,
        String comentario
) {
}
