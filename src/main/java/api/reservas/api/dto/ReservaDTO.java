package api.reservas.api.dto;

import api.reservas.api.entitys.Reserva;

public record ReservaDTO(

        Long id,
        Long usuario,
        Long vaga,
        Boolean mesaOcupada

) {
    public ReservaDTO(Reserva reserva){
        this(
                reserva.getId(),
                reserva.getUsuario().getId(),
                reserva.getVaga().getId(),
                reserva.getMesaOcupada()
        );
    }
}
