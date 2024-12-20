package api.reservas.api.domain.avaliacoes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import api.reservas.api.domain.enums.NotaAvaliacao;

class AvaliacaoTest {

	@Test
	void testAvaliacaoValida() {
		Long idReserva = 1L;
		NotaAvaliacao notaAvaliacao = NotaAvaliacao.CINCO;
		String comentario = "Ótimo!";

		Avaliacao avaliacao = new Avaliacao(idReserva, notaAvaliacao, comentario);

		assertNotNull(avaliacao);
		assertEquals(idReserva, avaliacao.idReserva());
		assertEquals(notaAvaliacao, avaliacao.notaAvaliacao());
		assertEquals(comentario, avaliacao.comentario());
	}

	@Test
	void testAvaliacaoComCamposNulos() {
		Long idReserva = null;
		NotaAvaliacao notaAvaliacao = null;
		String comentario = null;

		Avaliacao avaliacao = new Avaliacao(idReserva, notaAvaliacao, comentario);

		assertNotNull(avaliacao);
		assertEquals(idReserva, avaliacao.idReserva());
		assertEquals(notaAvaliacao, avaliacao.notaAvaliacao());
		assertEquals(comentario, avaliacao.comentario());
	}
}
