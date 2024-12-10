package api.reservas.api.dto;

import api.reservas.api.entitys.Restaurante;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RestauranteDTO(

        Long id,
        @NotBlank
        String nome,
        @NotBlank
        String endereco,
        @NotBlank
        String tipodecozinha,
        @NotNull
        String funcionamento,
        int capacidade,
        Boolean ativo
){
        public RestauranteDTO(Restaurante restaurante) {
                this(
                        restaurante.getId(),
                        restaurante.getNome(),
                        restaurante.getEndereco(),
                        restaurante.getTipodecozinha(),
                        restaurante.getFuncionamento(),
                        restaurante.getCapacidade(),
                        restaurante.getAtivo()
                );
        }


}