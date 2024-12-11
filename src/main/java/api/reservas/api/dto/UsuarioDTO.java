package api.reservas.api.dto;

import api.reservas.api.entitys.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioDTO(

        Long id,
        @NotBlank
        String nome,

        @NotNull

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