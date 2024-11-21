package api.reservas.api.controller;


import api.reservas.api.dto.*;
import api.reservas.api.entitys.Reserva;
import api.reservas.api.repository.ReservaRepository;
import api.reservas.api.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    public ReservaRepository reservaRepository;

    public final ReservaService reservaService;


    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public ResponseEntity cadastroReserva(@RequestBody ReservaDTO reservaDTO) {
        var idCadastro = reservaService.criarReserva(reservaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Reserva());
    }


    //Listar todos os cliente
    @GetMapping
    public ResponseEntity <List<Reserva>> findAll() {
        return ResponseEntity.ok(reservaService.findAll());
    }

    //Procurar por apenas 1 cliente
    // não esta funcionando. http 200 mas não aparece informação.
    @GetMapping("/id")
    public ResponseEntity<Reserva> findById(@PathVariable("id") Long id) {
        Reserva entity= reservaService.findById(id);
        return ResponseEntity.ok(entity);
    }
}
