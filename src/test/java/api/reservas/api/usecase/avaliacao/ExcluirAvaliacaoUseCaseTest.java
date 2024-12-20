package api.reservas.api.usecase.avaliacao;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import api.reservas.api.gateway.AvaliacaoGateway;
import api.reservas.api.usecase.avaliacoes.ExcluirAvaliacaoUseCase;

@ExtendWith(MockitoExtension.class)
class ExcluirAvaliacaoUseCaseTest {

	@Mock
	private AvaliacaoGateway avaliacaoGateway;

	@InjectMocks
	private ExcluirAvaliacaoUseCase excluirAvaliacaoUseCase;

	@Test
	void testExcluirAvaliacao() {
		Long idAvaliacao = 1L;

		excluirAvaliacaoUseCase.excluirAvaliacao(idAvaliacao);

		verify(avaliacaoGateway, times(1)).excluirAvaliacao(idAvaliacao);
	}
}
