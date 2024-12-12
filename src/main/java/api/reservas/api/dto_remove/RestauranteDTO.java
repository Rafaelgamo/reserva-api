package api.reservas.api.dto_remove;


import api.reservas.api.gateway.database.jpa.entity.RestauranteEntity;
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
        public RestauranteDTO(RestauranteEntity restauranteEntity) {
                this(
                        restauranteEntity.getId(),
                        restauranteEntity.getNome(),
                        null, //restauranteEntity.getEndereco(),
                        null, //restauranteEntity.getTipoCozinha(),
                        null, //restauranteEntity.getHorarioFuncionamento(),
                        restauranteEntity.getCapacidadeEmMesas(),
                        restauranteEntity.getAtivo()
                );
        }


}