package api.reservas.api.usecase.dto;

import api.reservas.api.domain.enums.NotaAvaliacao;

public record AvaliacaoComIdDTO(
        Long id,
        Long idReserva,
        NotaAvaliacao notaAvaliacao,
        String comentario
) { }
