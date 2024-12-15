package api.reservas.api.controller;

import api.reservas.api.controller.json.AlterarRestauranteJson;
import api.reservas.api.controller.json.CadastroRestauranteJson;
import api.reservas.api.controller.json.IdJson;
import api.reservas.api.controller.mapper.RestauranteDTOMapper;
import api.reservas.api.dto_remove.RestauranteDTO;
import api.reservas.api.services_remove.RestauranteService;
import api.reservas.api.usecase.dto.AlterarRestauranteDTO;
import api.reservas.api.usecase.restaurantes.AlterarRestauranteUseCase;
import api.reservas.api.usecase.restaurantes.CadastrarRestauranteUseCase;
import api.reservas.api.usecase.restaurantes.ExcluirRestauranteUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurante")
public class RestauranteController {

    private final RestauranteService restauranteService;
    private final CadastrarRestauranteUseCase cadastrarRestauranteUseCase;
    private final ExcluirRestauranteUseCase excluirRestauranteUseCase;
    private final AlterarRestauranteUseCase alterarRestauranteUseCase;

    private final RestauranteDTOMapper restauranteDTOMapper = new RestauranteDTOMapper();

    public RestauranteController(RestauranteService restauranteService, CadastrarRestauranteUseCase cadastrarRestauranteUseCase, ExcluirRestauranteUseCase excluirRestauranteUseCase, AlterarRestauranteUseCase alterarRestauranteUseCase) {
        this.restauranteService = restauranteService;
        this.cadastrarRestauranteUseCase = cadastrarRestauranteUseCase;
        this.excluirRestauranteUseCase = excluirRestauranteUseCase;
        this.alterarRestauranteUseCase = alterarRestauranteUseCase;
    }

    @PostMapping
    public ResponseEntity<IdJson> cadastro(@RequestBody @Valid CadastroRestauranteJson cadastroRestauranteJson) {
        var restauranteDTO = restauranteDTOMapper.mapToDTO(cadastroRestauranteJson);

        var idCadastro = cadastrarRestauranteUseCase.cadastrarRestaurante(restauranteDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new IdJson(idCadastro));
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<List<RestauranteDTO>> buscaDeRestaurantes(@PathVariable(name = "cnpj") @Size(min = 14, max = 18) String cnpj) {
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<RestauranteDTO> buscarRestaurante(@PathVariable(name = "cnpj") @Size(min = 14, max = 18) String cnpj) {
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<RestauranteDTO>> listarAtivos(@PageableDefault(size =10) Pageable paginacao){
        var page = restauranteService.listarAtivos(paginacao);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{cnpj}")
    public ResponseEntity<Void> alterarRestaurante(
            @PathVariable(name = "cnpj") @Size(min = 14, max = 18) String cnpj, @RequestBody AlterarRestauranteJson alterarRestauranteJson) {
        var alterarRestauranteDTO = new AlterarRestauranteDTO(alterarRestauranteJson);
        alterarRestauranteUseCase.alterarRestaurante(cnpj, alterarRestauranteDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{cnpj}")
    public ResponseEntity<Void> removerRestaurante(@PathVariable(name = "cnpj")
                                                       @Size(min = 14, max = 18) String cnpj) {
        excluirRestauranteUseCase.excluirRestaurante(cnpj);
        return ResponseEntity.noContent().build();
    }

}
