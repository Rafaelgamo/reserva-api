package api.reservas.api.dto;

import api.reservas.api.entitys.Usuario;

public record UsuarioDTO(
        Long id,
        String nome,

        String telefone
) {

    public UsuarioDTO(Usuario usuario){
        this( usuario.getId(), usuario.getNome(), usuario.getTelefone());
    }

}

