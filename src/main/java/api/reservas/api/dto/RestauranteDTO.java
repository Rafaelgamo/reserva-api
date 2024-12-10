package api.reservas.api.dto;


import api.reservas.api.gateway.database.jpa.entity.RestauranteEntity;

public record RestauranteDTO(
        Long id,
        String nome,
        String endereco,
        String tipodecozinha,
        String funcionamento,
        Integer capacidade,
        Boolean ativo
){
        public RestauranteDTO(RestauranteEntity restauranteEntity) {
                this(
                        restauranteEntity.getId(),
                        restauranteEntity.getNome(),
                        null, //restauranteEntity.getEndereco(),
                        null, //restauranteEntity.getTipoCozinha(),
                        null, //restauranteEntity.getHorarioFuncionamento(),
                        restauranteEntity.getCapacidade(),
                        restauranteEntity.getAtivo()
                );
        }

}