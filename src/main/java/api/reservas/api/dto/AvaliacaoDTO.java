package api.reservas.api.dto;

import api.reservas.api.domain.enums.NotaAvaliacao;

public record AvaliacaoDTO(

        Long idRestaurante,
        Long idUsuario,
        String comentario,
        NotaAvaliacao notaAvaliacao

) {
}
