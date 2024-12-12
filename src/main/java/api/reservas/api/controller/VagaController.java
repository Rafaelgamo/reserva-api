package api.reservas.api.controller;

import api.reservas.api.dto_remove.VagaDTO;
import api.reservas.api.entitys_remove.Vaga;
import api.reservas.api.repository_remove.VagaRepository;
import api.reservas.api.services_remove.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/vaga")
public class VagaController {

        @Autowired
        private VagaService vagaService;

        @Autowired
        private VagaRepository vagaRepository;

        // craiar vaga

        @PostMapping("/registrar")
        public ResponseEntity criarVaga(@RequestBody VagaDTO vagaDTO){
            var idCadastro = vagaService.criarVaga(vagaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Vaga());
        }


        //Listar vagas por id
        @GetMapping("/{id}")
        public ResponseEntity<VagaDTO> buscaId(@PathVariable(name = "id") Long id) {
            var vaga = vagaService.buscaPorId(id);
            return ResponseEntity.ok(new VagaDTO(vaga));
        }

        // Registrar liberação da mesa após saída do cliente


        //vaga removida
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> removerVaga(@PathVariable(name = "id") Long id) {
            vagaService.removerVaga(id);
            return ResponseEntity.noContent().build();
        }
    }



