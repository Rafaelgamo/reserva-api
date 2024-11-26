package api.reservas.api.services;

import api.reservas.api.dto.UsuarioDTO;
import api.reservas.api.entitys.Usuario;
import api.reservas.api.infra.errors.exceptions.EntidadeJaExiste;
import api.reservas.api.infra.errors.exceptions.ErroDeValidacao;
import api.reservas.api.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService (UsuarioRepository usuarioRepository){ this.usuarioRepository = usuarioRepository;}

    @Transactional
    public long cadastroUsuario(UsuarioDTO dados){
        var nome = dados.nome();
        var telefone = dados.telefone();
        var usuario = new Usuario();

        usuario.setNome(dados.nome());
        usuario.setTelefone(dados.telefone());

        boolean usuarioJaCadastrado = usuarioRepository.existsByNome(nome);
        if (usuarioJaCadastrado) {
            throw new EntidadeJaExiste(Usuario.class, "nome", nome);
        }

        boolean telefoneJaCadastrado = usuarioRepository.existsByTelefone(telefone);
        if(telefoneJaCadastrado){
            throw new EntidadeJaExiste(Usuario.class, "telefone", telefone);
        }

        var usuarioSalvo = usuarioRepository.save(usuario);
        if (usuarioSalvo == null) {
            throw new ErroDeValidacao("Erro ao cadastrar usuario");
        }

        return  usuarioSalvo.getId();
    }

    @Transactional
    public Page<UsuarioDTO> listarTodos(Pageable paginacao){
        return usuarioRepository.findAll(paginacao).map(UsuarioDTO::new);
    }


}

