package api.reservas.api.dto;


import api.reservas.api.entitys.Reserva;

public record ReservaDTO(
        Long id,
        Long idRestaurante,
        Long idUsuario,
        Integer quantidade,
        String horario,
        Boolean ativo
) {
    public ReservaDTO(Reserva reserva) {

        this(
                reserva.getId(),
                reserva.getRestaurante().getId(),
                reserva.getUsuario().getId(),
                reserva.getQuantidade(),
                reserva.getHorario(),
                reserva.getAtivo()
        );

    }
}