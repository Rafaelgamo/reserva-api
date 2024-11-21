package api.reservas.api.controller;

import api.reservas.api.dto.RestauranteDTO;
import api.reservas.api.entitys.Restaurante;
import api.reservas.api.services.RestauranteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/restaurante")
public class RestauranteController {

        public final RestauranteService restauranteService;

        public RestauranteController(RestauranteService restauranteService){this.restauranteService = restauranteService;}

        @PostMapping
        public ResponseEntity cadastro(@RequestBody RestauranteDTO restauranteDTO){
        var idCadastro = restauranteService.cadastroRestaurante(restauranteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Restaurante());
        }


        @GetMapping
        public ResponseEntity<Page<RestauranteDTO>> listar(@PageableDefault(size =10) Pageable paginacao){
            var page = restauranteService.listarTodos(paginacao);
            return ResponseEntity.ok(page);
        }



}








