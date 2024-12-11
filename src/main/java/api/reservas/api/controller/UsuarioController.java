package api.reservas.api.controller;

import api.reservas.api.dto_remove.UsuarioDTO;
import api.reservas.api.entitys_remove.Usuario;
import api.reservas.api.repository_remove.UsuarioRepository;
import api.reservas.api.services_remove.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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
        var page = usuarioService.listarAtivos(paginacao);
        return ResponseEntity.ok(page);
    }

       @DeleteMapping("/{id}")
        public ResponseEntity<Void> remover(@PathVariable(name = "id") Long id) {
        usuarioService.removerUsuario(id);
        return ResponseEntity.noContent().build();
    }


}