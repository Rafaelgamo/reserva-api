package api.reservas.api.controller;


import api.reservas.api.dto.*;
import api.reservas.api.entitys.Reserva;
import api.reservas.api.repository.ReservaRepository;
import api.reservas.api.services.ReservaService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    public  ReservaRepository reservaRepository;

    public final ReservaService reservaService;


    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    //cadastrar
    @PostMapping
    @Transactional
    public ResponseEntity cadastroReserva(@RequestBody ReservaDTO reservaDTO) {
        var idCadastro = reservaService.criarReserva(reservaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Reserva());
    }

    //listarTodas
    @GetMapping
    public ResponseEntity<Page<ReservaDTO>> listarTodos(@PageableDefault(size =10) Pageable paginacao){
        var page = reservaService.listarTodos(paginacao);
        return ResponseEntity.ok(page);
    }

    //cancelar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cacelarReserva(@PathVariable(name = "id") Long id) {
        reservaService.cancelarReserva(id);
        return ResponseEntity.noContent().build();
    }

    //Registrar Por horário

    //listar por id

    //resgistra liberação de mesa apos saida docliente
}
