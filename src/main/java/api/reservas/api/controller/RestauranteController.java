package api.reservas.api.controller;

import api.reservas.api.controller.json.AlterarRestauranteJson;
import api.reservas.api.controller.json.CadastroRestauranteJson;
import api.reservas.api.controller.json.IdJson;
import api.reservas.api.controller.mapper.RestauranteMapper;
import api.reservas.api.domain.paging.PagedResult;
import api.reservas.api.domain.paging.PagingInfo;
import api.reservas.api.usecase.dto.AlterarRestauranteDTO;
import api.reservas.api.usecase.dto.RestauranteDTO;
import api.reservas.api.usecase.restaurantes.AlterarRestauranteUseCase;
import api.reservas.api.usecase.restaurantes.BuscarRestaurantesUseCase;
import api.reservas.api.usecase.restaurantes.CadastrarRestauranteUseCase;
import api.reservas.api.usecase.restaurantes.ExcluirRestauranteUseCase;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
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
@RequestMapping("/restaurante")
public class RestauranteController {

    private final BuscarRestaurantesUseCase buscarRestaurantesUseCase;
    private final CadastrarRestauranteUseCase cadastrarRestauranteUseCase;
    private final ExcluirRestauranteUseCase excluirRestauranteUseCase;
    private final AlterarRestauranteUseCase alterarRestauranteUseCase;

    private final RestauranteMapper restauranteMapper = new RestauranteMapper();

    public RestauranteController(BuscarRestaurantesUseCase buscarRestaurantesUseCase,
                                 CadastrarRestauranteUseCase cadastrarRestauranteUseCase,
                                 ExcluirRestauranteUseCase excluirRestauranteUseCase,
                                 AlterarRestauranteUseCase alterarRestauranteUseCase) {
        this.buscarRestaurantesUseCase = buscarRestaurantesUseCase;
        this.cadastrarRestauranteUseCase = cadastrarRestauranteUseCase;
        this.excluirRestauranteUseCase = excluirRestauranteUseCase;
        this.alterarRestauranteUseCase = alterarRestauranteUseCase;
    }

    @PostMapping
    public ResponseEntity<IdJson> cadastro(@RequestBody @Valid CadastroRestauranteJson cadastroRestauranteJson) {
        var restauranteDTO = restauranteMapper.mapToDTO(cadastroRestauranteJson);

        var idCadastro = cadastrarRestauranteUseCase.cadastrarRestaurante(restauranteDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new IdJson(idCadastro));
    }

    @Operation(description = "Listar todos os restaurantes abertos no momento")
    @GetMapping
    public ResponseEntity<PagedResult<RestauranteDTO>> listarAbertosAgora(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "pageSize", required = false) Integer pageSize
    ) {
        var pagingInfo = PagingInfo.of(page, pageSize);
        var restaurantesFiltrados = buscarRestaurantesUseCase.filtrarAbertosNoMomento(pagingInfo);

        var dtoPage = restaurantesFiltrados.map(restauranteMapper::mapToDTO);
        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<RestauranteDTO> buscarPorCnpj(@PathVariable(name = "cnpj") @Size(min = 14, max = 18) String cnpj) {
        var domainRestaurante = buscarRestaurantesUseCase.buscarPorCnpj(cnpj);
        return ResponseEntity.ok(restauranteMapper.mapToDTO(domainRestaurante));
    }

    @GetMapping("/filtro/nome/{nome}")
    public ResponseEntity<PagedResult<RestauranteDTO>> filtrarRestaurantesPorNome(
            @PathVariable(name = "nome") String nome,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "pageSize", required = false) Integer pageSize
    ) {
        var pagingInfo = PagingInfo.of(page, pageSize);

        var restaurantesFiltrados = buscarRestaurantesUseCase.filtrarPorNome(nome, pagingInfo);

        var dtoPage = restaurantesFiltrados.map(restauranteMapper::mapToDTO);
        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/filtro/cep/{cep}")
    public ResponseEntity<PagedResult<RestauranteDTO>> filtrarRestaurantesPorCep(
            @PathVariable(name = "cep") @Size(min = 8, max = 9) String cep,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "pageSize", required = false) Integer pageSize
    ) {
        var pagingInfo = PagingInfo.of(page, pageSize);

        var restaurantesFiltrados = buscarRestaurantesUseCase.filtrarPorCep(cep, pagingInfo);

        var dtoPage = restaurantesFiltrados.map(restauranteMapper::mapToDTO);
        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/filtro/tipocozinha/{tipoCozinha}")
    public ResponseEntity<PagedResult<RestauranteDTO>> filtrarRestaurantesPorTipoCozinha(
            @PathVariable(name = "tipoCozinha") String tipoCozinha,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "pageSize", required = false) Integer pageSize
    ) {
        var pagingInfo = PagingInfo.of(page, pageSize);

        var restaurantesFiltrados = buscarRestaurantesUseCase.filtrarPorTipoCozinha(tipoCozinha, pagingInfo);

        var dtoPage = restaurantesFiltrados.map(restauranteMapper::mapToDTO);
        return ResponseEntity.ok(dtoPage);
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
