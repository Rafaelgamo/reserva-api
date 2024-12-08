package api.reservas.api.dto;

import api.reservas.api.entitys.Usuario;
import org.springframework.data.web.config.EnableSpringDataWebSupport;


@EnableSpringDataWebSupport
public record UsuarioDTO(
        Long id,
        String nome,
        String telefone,
        Boolean ativo
) {

    public UsuarioDTO(Usuario usuario){
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getTelefone(),
                usuario.getAtivo()

                );
    }

}