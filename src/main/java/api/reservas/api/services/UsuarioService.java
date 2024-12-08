package api.reservas.api.services;

import api.reservas.api.dto.UsuarioDTO;
import api.reservas.api.entitys.Usuario;
import api.reservas.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public long cadastroUsuario(UsuarioDTO dados) {
        var nome = dados.nome();
        var telefone = dados.telefone();
        var usuario = new Usuario();

        usuario.setNome(dados.nome());
        usuario.setTelefone(dados.telefone());
        usuario.setAtivo(true);

        var usuarioSalvo = usuarioRepository.save(usuario);

        return usuarioSalvo.getId();
    }

        @Transactional
        public Page<UsuarioDTO> listarTodos(Pageable paginacao) {
            return usuarioRepository.findAll(paginacao).map(UsuarioDTO::new);
        }


    @Transactional
    public void removerUsuario(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        usuario.ifPresent(value -> value.setAtivo(false));
    }


}


