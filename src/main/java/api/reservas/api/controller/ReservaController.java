package api.reservas.api.controller;

import api.reservas.api.controller.json.IdJson;
import api.reservas.api.controller.json.ReservaJson;
import api.reservas.api.domain.paging.PagedResult;
import api.reservas.api.domain.paging.PagingInfo;
import api.reservas.api.usecase.dto.ReservaDTO;
import api.reservas.api.usecase.reservas.AlterarReservaUseCase;
import api.reservas.api.usecase.reservas.BuscarReservasUseCase;
import api.reservas.api.usecase.reservas.FazerReservaUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

    private final FazerReservaUseCase fazerReservaUseCase;
    private final BuscarReservasUseCase buscarReservasUseCase;
    private final AlterarReservaUseCase alterarReservaUseCase;

    public ReservaController(FazerReservaUseCase fazerReservaUseCase, BuscarReservasUseCase buscarReservasUseCase, AlterarReservaUseCase alterarReservaUseCase) {
        this.fazerReservaUseCase = fazerReservaUseCase;
        this.buscarReservasUseCase = buscarReservasUseCase;
        this.alterarReservaUseCase = alterarReservaUseCase;
    }

    @PostMapping()
    public ResponseEntity<IdJson> fazerReserva(@RequestBody @Valid ReservaJson reservaJson) {
        var reservaDTO = mapToDTO(reservaJson);
        var reservaId = fazerReservaUseCase.fazerReserva(reservaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new IdJson(reservaId));
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<PagedResult<ReservaDTO>> listarPorRestaurante(
            @PathVariable(name = "cnpj") @Size(min = 11, max = 14) String cnpj,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "pageSize", required = false) Integer pageSize
    ) {
        var pagingInfo = PagingInfo.of(page, pageSize);

        buscarReservasUseCase.buscarPorCnpj(cnpj, pagingInfo);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{id_reserva}")
    public ResponseEntity<Void> concluirReserva(@PathVariable(name = "id") @Positive Long id) {
        alterarReservaUseCase.concluirReserva(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id_reserva}")
    public ResponseEntity<Void> cancelarReserva(@PathVariable(name = "id") @Positive Long id) {
        alterarReservaUseCase.cancelarReserva(id);
        return ResponseEntity.noContent().build();
    }

    private ReservaDTO mapToDTO(ReservaJson reservaJson) {
        return new ReservaDTO(reservaJson.cnpjRestaurante(), reservaJson.data());
    }
}
