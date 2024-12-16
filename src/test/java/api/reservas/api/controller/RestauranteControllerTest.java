package api.reservas.api.controller;

import api.reservas.api.domain.paging.PagedResult;
import api.reservas.api.domain.paging.PagingInfo;
import api.reservas.api.domain.restaurante.Restaurante;
import api.reservas.api.usecase.restaurantes.BuscarRestaurantesUseCase;
import api.reservas.api.usecase.restaurantes.CadastrarRestauranteUseCase;
import api.reservas.api.usecase.restaurantes.ExcluirRestauranteUseCase;
import api.reservas.api.usecase.restaurantes.AlterarRestauranteUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import org.springframework.data.domain.PageImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestauranteController.class)
public class RestauranteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BuscarRestaurantesUseCase buscarRestaurantesUseCase;

    @MockBean
    private CadastrarRestauranteUseCase cadastrarRestauranteUseCase;

    @MockBean
    private ExcluirRestauranteUseCase excluirRestauranteUseCase;

    @MockBean
    private AlterarRestauranteUseCase alterarRestauranteUseCase;

    @BeforeEach
    void setUp() {
        PagedResult<Restaurante> pagedResult = PagedResult.of(new PageImpl<>(new ArrayList<>()), PagingInfo.of(1, 10));
        Mockito.when(buscarRestaurantesUseCase.filtrarAbertosNoMomento(Mockito.any(PagingInfo.class))).thenReturn(pagedResult);
    }

    @Test
    void deveListarRestaurantesAbertosAgora() throws Exception {
        mockMvc.perform(get("/restaurante")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}