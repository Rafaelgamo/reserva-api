package api.reservas.api.controller;

import api.reservas.api.dto_remove.AvaliacaoDTO;
import api.reservas.api.repository_remove.AvaliacaoRepository;
import api.reservas.api.services_remove.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/avaliar")
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
        var page = avaliacaoService.findAll(paginacao);
        return ResponseEntity.ok(page);
    }
}
