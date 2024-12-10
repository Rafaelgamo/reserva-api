package api.reservas.api.dto;

import api.reservas.api.Enum.NotaAvaliacao;
import api.reservas.api.entitys.Avaliacao;

public record AvaliacaoDTO(

        Long id,
        NotaAvaliacao notaAvaliacao,
        Long idRestaurante,
        Long idReserva,
        String comentario
) {
    public AvaliacaoDTO (Avaliacao avaliacao){
        this(
                avaliacao.getId(),
                avaliacao.getNotaAvaliacao(),
                avaliacao.getRestaurante().getId(),
                avaliacao.getReserva().getId(),
                avaliacao.getComentario());
    }
}
