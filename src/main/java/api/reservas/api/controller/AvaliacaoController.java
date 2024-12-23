package api.reservas.api.controller;

import api.reservas.api.controller.json.AvaliacaoJson;
import api.reservas.api.domain.enums.NotaAvaliacao;
import api.reservas.api.domain.paging.PagedResult;
import api.reservas.api.domain.paging.PagingInfo;
import api.reservas.api.usecase.avaliacoes.AvaliarReservaUseCase;
import api.reservas.api.usecase.avaliacoes.BuscarAvaliacoesUseCase;
import api.reservas.api.usecase.avaliacoes.ExcluirAvaliacaoUseCase;
import api.reservas.api.usecase.dto.AvaliacaoComIdDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    private final AvaliarReservaUseCase avaliarReservaUseCase;
    private final BuscarAvaliacoesUseCase buscarAvaliacoesUseCase;
    private final ExcluirAvaliacaoUseCase excluirAvaliacaoUseCase;

    public AvaliacaoController(AvaliarReservaUseCase avaliarReservaUseCase, BuscarAvaliacoesUseCase buscarAvaliacoesUseCase, ExcluirAvaliacaoUseCase excluirAvaliacaoUseCase) {
        this.avaliarReservaUseCase = avaliarReservaUseCase;
        this.buscarAvaliacoesUseCase = buscarAvaliacoesUseCase;
        this.excluirAvaliacaoUseCase = excluirAvaliacaoUseCase;
    }

    @Operation(description = "Avaliar experiência na reserva")
    @PostMapping("/{id_reserva}")
    public ResponseEntity<Void> fazerAvaliacao(
            @PathVariable("id_reserva") @Positive Long idReserva,
            @RequestBody @Valid AvaliacaoJson avaliacaoJson
    ) {
        var notaAvaliacao = NotaAvaliacao.of(avaliacaoJson.notaAvaliacao());
        avaliarReservaUseCase.fazerAvaliacao(idReserva, notaAvaliacao, avaliacaoJson.comentario());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(description = "Listar avaliações do restaurante")
    @GetMapping("/{cnpj}")
    public ResponseEntity<PagedResult<AvaliacaoComIdDTO>> buscarPorCnpj(
            @PathVariable("cnpj") String cnpj,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "pageSize", required = false) Integer pageSize
    ) {
        var pagingInfo = PagingInfo.of(page, pageSize);
        var avaliacaoPagedResult = buscarAvaliacoesUseCase.buscarPorCnpj(cnpj, pagingInfo);
        return ResponseEntity.ok(avaliacaoPagedResult);
    }


    @Operation(description = "Excluir avaliação")
    @DeleteMapping("/{id_avaliacao}")
    public ResponseEntity<Void> excluirAvaliacao(@PathVariable @Positive Long idAvaliacao) {
        excluirAvaliacaoUseCase.excluirAvaliacao(idAvaliacao);
        return ResponseEntity.noContent().build();
    }

}
