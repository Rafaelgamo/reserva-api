package api.reservas.api.dto;

import api.reservas.api.Enum.NotaAvaliacao;
import api.reservas.api.entitys.Restaurante;
import api.reservas.api.entitys.Usuario;

public record AvaliacaoDTO(

        Long idRestaurante,
        Long idUsuario,
        String comentario,
        NotaAvaliacao notaAvaliacao

) {
}
