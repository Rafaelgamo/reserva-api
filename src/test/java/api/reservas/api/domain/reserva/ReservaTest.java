package api.reservas.api.domain.reserva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import api.reservas.api.domain.enums.StatusReserva;

class ReservaTest {

	@Test
	void testReservaValida() {
		String cnpjRestaurante = "12345678901234";
		LocalDateTime data = LocalDateTime.of(2024, 12, 25, 19, 0);
		StatusReserva statusReserva = StatusReserva.CONCLUIDA;

		Reserva reserva = new Reserva(cnpjRestaurante, data, statusReserva);

		assertNotNull(reserva);
		assertEquals(cnpjRestaurante, reserva.cnpjRestaurante());
		assertEquals(data, reserva.data());
		assertEquals(statusReserva, reserva.statusReserva());
	}

	@Test
	void testReservaComCamposNulos() {
		String cnpjRestaurante = null;
		LocalDateTime data = null;
		StatusReserva statusReserva = null;

		Reserva reserva = new Reserva(cnpjRestaurante, data, statusReserva);

		assertNotNull(reserva);
		assertEquals(cnpjRestaurante, reserva.cnpjRestaurante());
		assertEquals(data, reserva.data());
		assertEquals(statusReserva, reserva.statusReserva());
	}
}
