package api.reservas.api.controller;


import api.reservas.api.dto.AvaliacaoDTO;
import api.reservas.api.dto.ReservaDTO;
import api.reservas.api.dto.RestauranteDTO;
import api.reservas.api.dto.VagaDTO;
import api.reservas.api.entitys.Vaga;
import api.reservas.api.repository.ReservaRepository;
import api.reservas.api.repository.VagaRepository;
import api.reservas.api.services.AvaliacaoService;
import api.reservas.api.services.ReservaService;
import api.reservas.api.services.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private ReservaRepository reservaRepository;

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
