package api.reservas.api.domain.avaliacoes;

import api.reservas.api.domain.enums.NotaAvaliacao;

public record Avaliacao (
        Long idReserva,
        NotaAvaliacao notaAvaliacao,
        String comentario
) { }
