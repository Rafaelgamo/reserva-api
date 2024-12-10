package api.reservas.api.controller;

import api.reservas.api.dto.AvaliacaoDTO;
import api.reservas.api.dto.UsuarioDTO;
import api.reservas.api.entitys.Avaliacao;
import api.reservas.api.entitys.Vaga;
import api.reservas.api.repository.AvaliacaoRepository;
import api.reservas.api.services.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;
    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @PostMapping
    public ResponseEntity criarAvaliacao(@RequestBody AvaliacaoDTO avaliacaoDTO){
        var idCadastro = avaliacaoService.criarAvaliacao(avaliacaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Vaga());
    }

    //get nao funciona
    @GetMapping
    public ResponseEntity<Page<AvaliacaoDTO>> listarTodos(@PageableDefault(size =10) Pageable paginacao){
        var page = avaliacaoService.listar(paginacao);
        return ResponseEntity.ok(page);
    }
}
