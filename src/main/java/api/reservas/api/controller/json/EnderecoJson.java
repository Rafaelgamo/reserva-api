package api.reservas.api.controller.json;

import jakarta.validation.constraints.Size;

public record EnderecoJson(
        @Size(min = 8, max = 9) String cep,
        String numero
) {
}
