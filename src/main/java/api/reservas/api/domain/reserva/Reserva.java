package api.reservas.api.domain.reserva;

import api.reservas.api.domain.enums.StatusReserva;

import java.time.LocalDateTime;

public record Reserva (
        String cnpjRestaurante,
        LocalDateTime data,
        StatusReserva statusReserva
) {
    public Reserva(String cnpjRestaurante, LocalDateTime data, StatusReserva statusReserva) {
        this.cnpjRestaurante = cnpjRestaurante;
        this.data = data;
        this.statusReserva = statusReserva;
    }
}
