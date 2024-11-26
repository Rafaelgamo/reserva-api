package api.reservas.api.dto;


import api.reservas.api.entitys.Reserva;

public record ReservaDTO(
        Long idRestaurante,
        Long idUsuario,
        Integer quantidade,
        String horario
) {
    public ReservaDTO(Reserva reserva) {

        this(
                reserva.getRestaurante().getId(),
                reserva.getUsuario().getId(),
                reserva.getQuantidade(),
                reserva.getHorario()
        );

    }
}