package api.reservas.api.domain.restaurante;

import api.reservas.api.exception.ValidacaoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RestauranteTest {

    @Test
    void deveCriarRestauranteComDadosValidos() {
        Endereco endereco = new Endereco("12345678", "12345678");
        Restaurante restaurante = new Restaurante("Restaurante 12345678", "48278353000184", endereco, "Italiana", 10, 22, 20);

        assertNotNull(restaurante);
        assertEquals(endereco, restaurante.endereco());
    }

    @Test
    void deveLancarExcecaoParaNomeInvalido() {
        Endereco endereco = new Endereco("12345678", "12345678");
        assertThrows(ValidacaoException.class, () -> new Restaurante("", "48278353000184", endereco, "Italiana", 10, 22, 20));
    }

    @Test
    void deveLancarExcecaoParaCnpjInvalido() {
        Endereco endereco = new Endereco("12345678", "12345678");
        assertThrows(ValidacaoException.class, () -> new Restaurante("Restaurante 12345678", "12345678", endereco, "Italiana", 10, 22, 20));
    }

    @Test
    void deveLancarExcecaoParaEnderecoInvalido() {
        assertThrows(ValidacaoException.class, () -> new Restaurante("Restaurante 12345678", "48278353000184", null, "Italiana", 10, 22, 20));
    }

    @Test
    void deveLancarExcecaoParaTipoCozinhaInvalido() {
        Endereco endereco = new Endereco("12345678", "12345678");
        assertThrows(ValidacaoException.class, () -> new Restaurante("Restaurante 12345678", "48278353000184", endereco, "", 10, 22, 20));
    }

    @Test
    void deveLancarExcecaoParaCapacidadeEmMesasNegativa() {
        Endereco endereco = new Endereco("12345678", "12345678");
        assertThrows(ValidacaoException.class, () -> new Restaurante("Restaurante 12345678", "48278353000184", endereco, "Italiana", 10, 22, -1));
    }

    @Test
    void deveLancarExcecaoParaHoraAberturaInvalida() {
        Endereco endereco = new Endereco("12345678", "12345678");
        assertThrows(ValidacaoException.class, () -> new Restaurante("Restaurante 12345678", "48278353000184", endereco, "Italiana", -1, 22, 20));
        assertThrows(ValidacaoException.class, () -> new Restaurante("Restaurante 12345678", "48278353000184", endereco, "Italiana", 25, 22, 20));
    }

    @Test
    void deveLancarExcecaoParaHoraFechamentoInvalida() {
        Endereco endereco = new Endereco("12345678", "12345678");
        assertThrows(ValidacaoException.class, () -> new Restaurante("Restaurante 12345678", "48278353000184", endereco, "Italiana", 10, -1, 20));
        assertThrows(ValidacaoException.class, () -> new Restaurante("Restaurante 12345678", "48278353000184", endereco, "Italiana", 10, 25, 20));
    }
}