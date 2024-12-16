package api.reservas.api.controller.json;

import api.reservas.api.domain.enums.NotaAvaliacao;

public record AvaliacaoJson(
        NotaAvaliacao notaAvaliacao,
        String comentario
) {
}
