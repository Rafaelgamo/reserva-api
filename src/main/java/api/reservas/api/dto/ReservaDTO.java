package api.reservas.api.dto;


import api.reservas.api.entitys.Reserva;
import org.springframework.data.web.config.EnableSpringDataWebSupport;


@EnableSpringDataWebSupport
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