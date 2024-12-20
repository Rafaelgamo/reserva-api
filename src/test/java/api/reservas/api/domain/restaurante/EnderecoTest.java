package api.reservas.api.domain.restaurante;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import api.reservas.api.exception.ValidacaoException;
import api.reservas.api.util.ValidadorFormatoUtil;

@ExtendWith(MockitoExtension.class)
class EnderecoTest {

	@Test
	void testEnderecoValido() {
		try (MockedStatic<ValidadorFormatoUtil> mockedValidator = mockStatic(ValidadorFormatoUtil.class)) {
			String cepValido = "12345678";
			String numero = "123";
			mockedValidator.when(() -> ValidadorFormatoUtil.formatoCepValido(cepValido)).thenReturn(true);

			assertDoesNotThrow(() -> new Endereco(cepValido, numero));
		}
	}

	@Test
	void testEnderecoInvalidoCepVazio() {
		try (MockedStatic<ValidadorFormatoUtil> mockedValidator = mockStatic(ValidadorFormatoUtil.class)) {
			String cepInvalido = " ";
			String numero = "123";

			assertThrows(ValidacaoException.class, () -> new Endereco(cepInvalido, numero));
		}
	}

	@Test
	void testEnderecoInvalidoFormatoCep() {
		try (MockedStatic<ValidadorFormatoUtil> mockedValidator = mockStatic(ValidadorFormatoUtil.class)) {
			String cepInvalido = "123";
			String numero = "123";
			mockedValidator.when(() -> ValidadorFormatoUtil.formatoCepValido(cepInvalido)).thenReturn(false);

			assertThrows(ValidacaoException.class, () -> new Endereco(cepInvalido, numero));
		}
	}
}
