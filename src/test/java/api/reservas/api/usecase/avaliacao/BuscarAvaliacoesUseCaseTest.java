package api.reservas.api.usecase.avaliacao;

import api.reservas.api.domain.paging.PagedResult;
import api.reservas.api.domain.paging.PagingInfo;
import api.reservas.api.gateway.AvaliacaoGateway;
import api.reservas.api.usecase.avaliacoes.BuscarAvaliacoesUseCase;
import api.reservas.api.usecase.dto.AvaliacaoComIdDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class BuscarAvaliacoesUseCaseTest {

    private AvaliacaoGateway avaliacaoGateway;
    private BuscarAvaliacoesUseCase buscarAvaliacoesUseCase;

    @BeforeEach
    void setUp() {
        avaliacaoGateway = Mockito.mock(AvaliacaoGateway.class);
        buscarAvaliacoesUseCase = new BuscarAvaliacoesUseCase(avaliacaoGateway);
    }

    @Test
    void deveBuscarAvaliacoesPorCnpj() {
        List<AvaliacaoComIdDTO> avaliacoes = new ArrayList<>();
        Page<AvaliacaoComIdDTO> pageAvaliacoes = new PageImpl<>(avaliacoes);
        PagedResult<AvaliacaoComIdDTO> pagedResult = PagedResult.of(pageAvaliacoes, PagingInfo.of(1, 10));
        when(avaliacaoGateway.listarPorCnpj(anyString(), any(PagingInfo.class))).thenReturn(pagedResult);

        PagedResult<AvaliacaoComIdDTO> result = buscarAvaliacoesUseCase.buscarPorCnpj("12345678901234", PagingInfo.of(1, 10));

        assertEquals(pagedResult, result);
    }
}