package api.reservas.api.dto;

import api.reservas.api.entitys.Usuario;
import jakarta.validation.constraints.NotNull;


public record AlterarUsuarioDTO(
        @NotNull
        Long id,

        String nome,

        String telefone


) {
        public AlterarUsuarioDTO(Usuario usuario){
                this(
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getTelefone());
        }

}

