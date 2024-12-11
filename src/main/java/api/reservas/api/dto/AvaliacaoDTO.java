package api.reservas.api.dto;

import api.reservas.api.domain.enums.NotaAvaliacao;
import api.reservas.api.entitys.Avaliacao;

public record AvaliacaoDTO(

        Long id,
        NotaAvaliacao notaAvaliacao,
        String comentario
) {
    public AvaliacaoDTO(Avaliacao avaliacao) {
        this(
                avaliacao.getId(),
                avaliacao.getNotaAvaliacao(),
                avaliacao.getComentario()

        );
    }
}