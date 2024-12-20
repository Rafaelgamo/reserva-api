package api.reservas.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class HealthCheckControllerTest {

	@InjectMocks
	private HealthCheckController healthCheckController;

	@Test
	void testInfo() {
		ResponseEntity<HealthCheckController.ApiInfo> response = healthCheckController.info();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("RESERVA-API: Rodando", response.getBody().info());
		assertEquals("TODO: /swagger-ui.html", response.getBody().documentacao());
	}
}
