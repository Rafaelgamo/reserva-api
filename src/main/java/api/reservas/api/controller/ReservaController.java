package api.reservas.api.controller;

import api.reservas.api.dto_remove.ReservaDTO;
import api.reservas.api.entitys_remove.Vaga;
import api.reservas.api.repository_remove.ReservaRepository_Remove;
import api.reservas.api.services_remove.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private ReservaRepository_Remove reservaRepositoryRemove;

    // craiar reserva
    @PostMapping("/registrar")
    public ResponseEntity criarVaga(@RequestBody ReservaDTO reservaDTO){
        var idCadastro = reservaService.criarReserva(reservaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Vaga());
    }

    //listar todas as reserva ativas
    @GetMapping
    public ResponseEntity<Page<ReservaDTO>> listarAtivos(@PageableDefault(size =10) Pageable paginacao){
        var page = reservaService.listar(paginacao);
        return ResponseEntity.ok(page);
        }
    //cancelar reserva
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerReserva(@PathVariable(name = "id") Long id) {
        reservaService.removerReserva(id);
        return ResponseEntity.noContent().build();
        }


    }
