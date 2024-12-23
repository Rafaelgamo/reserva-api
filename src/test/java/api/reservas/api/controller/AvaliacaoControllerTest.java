package api.reservas.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import api.reservas.api.domain.paging.PagedResult;
import api.reservas.api.domain.paging.PagingInfo;
import api.reservas.api.usecase.avaliacoes.AvaliarReservaUseCase;
import api.reservas.api.usecase.avaliacoes.BuscarAvaliacoesUseCase;
import api.reservas.api.usecase.avaliacoes.ExcluirAvaliacaoUseCase;
import api.reservas.api.usecase.dto.AvaliacaoComIdDTO;

@WebMvcTest(AvaliacaoController.class)
public class AvaliacaoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AvaliarReservaUseCase avaliarReservaUseCase;

	@MockBean
	private BuscarAvaliacoesUseCase buscarAvaliacoesUseCase;

	@MockBean
	private ExcluirAvaliacaoUseCase excluirAvaliacaoUseCase;

	@Test
	void deveCriarAvaliacao() throws Exception {
		Mockito.doNothing().when(avaliarReservaUseCase).fazerAvaliacao(Mockito.anyLong(), Mockito.any(),
				Mockito.anyString());

		mockMvc.perform(post("/avaliacao/1").contentType(MediaType.APPLICATION_JSON)
				.content("{\"notaAvaliacao\": \"CINCO\", \"comentario\": \"Ótimo serviço\"}"))
				.andExpect(status().isCreated());
	}

	@Test
	void deveBuscarAvaliacoesPorCnpj() throws Exception {
		List<AvaliacaoComIdDTO> avaliacoes = new ArrayList<>();
		Page<AvaliacaoComIdDTO> pageAvaliacoes = new PageImpl<>(avaliacoes);
		PagedResult<AvaliacaoComIdDTO> pagedResult = PagedResult.of(pageAvaliacoes, PagingInfo.of(1, 10));
		Mockito.when(buscarAvaliacoesUseCase.buscarPorCnpj(Mockito.anyString(), Mockito.any(PagingInfo.class)))
				.thenReturn(pagedResult);

		mockMvc.perform(get("/avaliacao/12345678901234").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void deveExcluirAvaliacao() throws Exception {
		Mockito.doNothing().when(excluirAvaliacaoUseCase).excluirAvaliacao(Mockito.anyLong());

		mockMvc.perform(delete("/avaliacao/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}
}