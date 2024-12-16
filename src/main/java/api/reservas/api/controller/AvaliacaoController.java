package api.reservas.api.controller;

import api.reservas.api.controller.json.AvaliacaoJson;
import api.reservas.api.usecase.avaliacoes.AvaliarReservaUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    private final AvaliarReservaUseCase avaliarReservaUseCase;

    public AvaliacaoController(AvaliarReservaUseCase avaliarReservaUseCase) {
        this.avaliarReservaUseCase = avaliarReservaUseCase;
    }

    @PostMapping("/{id_reserva}")
    public ResponseEntity<Void> fazerAvaliacao(
            @PathVariable("id_reserva") @Positive Long idReserva,
            @RequestBody @Valid AvaliacaoJson avaliacaoJson) {
        avaliarReservaUseCase.fazerAvaliacao(idReserva, avaliacaoJson.notaAvaliacao(), avaliacaoJson.comentario());
        return ResponseEntity.ok().build();
    }

    //PagedResult<Avaliacao> listarPorCnpj(String cnpjRestaurante);

    //void excluirAvaliacao(Long idAvaliacao);

}
