package api.reservas.api.controller;



import api.reservas.api.dto.AlterarUsuarioDTO;
import api.reservas.api.dto.UsuarioDTO;
import api.reservas.api.entitys.Usuario;
import api.reservas.api.repository.UsuarioRepository;
import api.reservas.api.services.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    public  UsuarioRepository usuarioRepository;

    public final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){this.usuarioService = usuarioService;}

    @PostMapping
    @Transactional
    public ResponseEntity cadastro(@RequestBody @Valid UsuarioDTO usuarioDTO){
        var idCadastro = usuarioService.cadastroUsuario(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Usuario());
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioDTO>> listarTodos(@PageableDefault(size =10) Pageable paginacao){
        var page = usuarioService.listarTodos(paginacao);
        return ResponseEntity.ok(page);
    }

       @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable(name = "id") Long id) {
        usuarioService.removerUsuario(id);
        return ResponseEntity.noContent().build();
    }


}