package api.reservas.api.usecase.avaliacao;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import api.reservas.api.domain.enums.NotaAvaliacao;
import api.reservas.api.domain.enums.StatusReserva;
import api.reservas.api.domain.reserva.Reserva;
import api.reservas.api.exception.RecursoNaoEncontradoException;
import api.reservas.api.exception.ValidacaoException;
import api.reservas.api.gateway.AvaliacaoGateway;
import api.reservas.api.gateway.ReservaGateway;
import api.reservas.api.gateway.database.jpa.entity.ReservaEntity;
import api.reservas.api.usecase.avaliacoes.AvaliarReservaUseCase;

@ExtendWith(MockitoExtension.class)
class AvaliarReservaUseCaseTest {

	@Mock
	private AvaliacaoGateway avaliacaoGateway;

	@Mock
	private ReservaGateway reservaGateway;

	@InjectMocks
	private AvaliarReservaUseCase avaliarReservaUseCase;

	private ReservaEntity reservaConcluida;
	private ReservaEntity reservaNaoConcluida;

	@BeforeEach
	void setup() {
		reservaConcluida = new ReservaEntity();
		reservaConcluida.setStatus(StatusReserva.CONCLUIDA);

		reservaNaoConcluida = new ReservaEntity();
		reservaNaoConcluida.setStatus(StatusReserva.AGENDADA);
	}

	@Test
	void testFazerAvaliacaoComReservaConcluida() {
		Long idReserva = 1L;
		NotaAvaliacao notaAvaliacao = NotaAvaliacao.CINCO;
		String comentario = "Ótimo atendimento!";

		Reserva mockReservaConcluida = new Reserva(comentario, null, StatusReserva.CONCLUIDA);

		when(reservaGateway.buscarPorId(idReserva)).thenReturn(mockReservaConcluida);

		avaliarReservaUseCase.fazerAvaliacao(idReserva, notaAvaliacao, comentario);

		verify(avaliacaoGateway, times(1)).fazerAvaliacao(idReserva, notaAvaliacao, comentario);
	}

	@Test
	void testFazerAvaliacaoComReservaNaoEncontrada() {
		Long idReserva = 1L;

		when(reservaGateway.buscarPorId(idReserva)).thenReturn(null);

		assertThrows(RecursoNaoEncontradoException.class,
				() -> avaliarReservaUseCase.fazerAvaliacao(idReserva, NotaAvaliacao.QUATRO, "Comentário"));

		verify(avaliacaoGateway, never()).fazerAvaliacao(any(), any(), any());
	}

	@Test
	void testFazerAvaliacaoComReservaNaoConcluida() {
		Long idReserva = 1L;

		String comentario = "Ótimo atendimento!";

		Reserva mockReservaConcluida = new Reserva(comentario, null, StatusReserva.AGENDADA);

		when(reservaGateway.buscarPorId(idReserva)).thenReturn(mockReservaConcluida);

		assertThrows(ValidacaoException.class,
				() -> avaliarReservaUseCase.fazerAvaliacao(idReserva, NotaAvaliacao.TRES, "Comentário"));

		verify(avaliacaoGateway, never()).fazerAvaliacao(any(), any(), any());
	}
}
