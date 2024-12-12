package api.reservas.api.controller;

import api.reservas.api.controller.json.CadastroRestauranteJson;
import api.reservas.api.controller.json.IdRecursoJson;
import api.reservas.api.controller.mapper.RestauranteDTOMapper;
import api.reservas.api.dto_remove.RestauranteDTO;
import api.reservas.api.services_remove.RestauranteService;
import api.reservas.api.usecase.restaurantes.CadastrarRestauranteUseCase;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurante")
public class RestauranteController {

    private final RestauranteService restauranteService;
    private final CadastrarRestauranteUseCase cadastrarRestauranteUseCase;

    private final RestauranteDTOMapper restauranteDTOMapper = new RestauranteDTOMapper();

    public RestauranteController(RestauranteService restauranteService, CadastrarRestauranteUseCase cadastrarRestauranteUseCase) {
        this.restauranteService = restauranteService;
        this.cadastrarRestauranteUseCase = cadastrarRestauranteUseCase;
    }

    @PostMapping
    public ResponseEntity<IdRecursoJson> cadastro(@RequestBody @Valid CadastroRestauranteJson cadastroRestauranteJson) {
        var restauranteDTO = restauranteDTOMapper.mapToDTO(cadastroRestauranteJson);

        var idCadastro = cadastrarRestauranteUseCase.cadastrarRestaurante(restauranteDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new IdRecursoJson(idCadastro));
    }


    //listar todos restaurantes
    @GetMapping
    public ResponseEntity<Page<RestauranteDTO>> listarAtivos(@PageableDefault(size =10) Pageable paginacao){
        var page = restauranteService.listarAtivos(paginacao);
        return ResponseEntity.ok(page);
    }

    //cancelamento de cadastro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerRestaurante(@PathVariable(name = "id") Long id) {
        restauranteService.removerRestaurante(id);
        return ResponseEntity.noContent().build();
    }

    //criar indice para os campos nome, localização e tipo de cozinha

        /*@GetMapping("/nome/{nome}")
        public ResponseEntity<List<RestauranteEntity>> buscarPorNome(@PathVariable (name = "nome") String nomeRestaurante){
                var page = restauranteService.buscarPorNome(nomeRestaurante);
                return ResponseEntity.ok(page);
        }
        @GetMapping("/endereco/{endereco}")
        public ResponseEntity<Optional<RestauranteEntity>> buscarPorEndereco(@PathVariable (name = "endereco") String enderecoRestaurante){
                var page = restauranteService.buscarPorEndereco(enderecoRestaurante);
                return ResponseEntity.ok(page);
        }

        @GetMapping("/cozinha/{cozinha}")
        public ResponseEntity<List<RestauranteEntity>> buscarPorCozinha(@PathVariable (name = "tipodecozinha") String tipodecozinha){
                var page = restauranteService.buscarPorCozinha(tipodecozinha);
                return ResponseEntity.ok(page);
        }*/


}



