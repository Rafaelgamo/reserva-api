package api.reservas.api.controller;

import api.reservas.api.dto.RestauranteDTO;
import api.reservas.api.gateway.database.jpa.entity.RestauranteEntity;
import api.reservas.api.services.RestauranteService;
import jakarta.transaction.Transactional;
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

        public final RestauranteService restauranteService;

        public RestauranteController(RestauranteService restauranteService){this.restauranteService = restauranteService;}


        // novo cadastro
        @PostMapping
        @Transactional
        public ResponseEntity cadastro(@RequestBody RestauranteDTO restauranteDTO) {
                var idCadastro = restauranteService.cadastroRestaurante(restauranteDTO);
                return ResponseEntity.status(HttpStatus.CREATED).body(new RestauranteEntity());
        }


        //listar todos restaurantes
        @GetMapping
        public ResponseEntity<Page<RestauranteDTO>> listarTodos(@PageableDefault(size =10) Pageable paginacao){
                var page = restauranteService.listarTodos(paginacao);
                return ResponseEntity.ok(page);
        }

        //cancelamento de cadastro
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> removerRestaurante(@PathVariable(name = "id") Long id) {
                restauranteService.removerRestaurante(id);
                return ResponseEntity.noContent().build();
        }


        //criar indice para os campos nome, localização e tipo de cozinha
}



