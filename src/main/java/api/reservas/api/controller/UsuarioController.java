package api.reservas.api.controller;



import api.reservas.api.dto.UsuarioDTO;
import api.reservas.api.entitys.Usuario;
import api.reservas.api.services.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    public final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){this.usuarioService = usuarioService;}


    @PostMapping
    public ResponseEntity cadastro(@RequestBody UsuarioDTO usuarioDTO){
        var idCadastro = usuarioService.cadastroUsuario(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Usuario());
    }


    @GetMapping
    public ResponseEntity<Page<UsuarioDTO>> listarTodos(@PageableDefault(size =10) Pageable paginacao){
        var page = usuarioService.listarTodos(paginacao);
        return ResponseEntity.ok(page);
    }
}
