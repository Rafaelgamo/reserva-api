package api.reservas.api.services;

import api.reservas.api.dto.UsuarioDTO;
import api.reservas.api.entitys.Usuario;
import api.reservas.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Long cadastroUsuario(UsuarioDTO dados) {
        var usuario = new Usuario();

        usuario.setNome(dados.nome());
        usuario.setTelefone(dados.telefone());
        usuario.setAtivo(true);

        var usuarioSalvo = usuarioRepository.save(usuario);
        return usuarioSalvo.getId();
    }

        @Transactional
        public Page<UsuarioDTO> listarAtivos(Pageable paginacao) {
            return usuarioRepository.findAllByAtivoTrue(paginacao).map(UsuarioDTO::new);
        }

        @Transactional
        public void removerUsuario(Long id) {
            Optional<Usuario> usuario = usuarioRepository.findById(id);
            usuario.ifPresent(value -> value.setAtivo(false));
        }

}


