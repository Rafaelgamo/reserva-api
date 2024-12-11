package api.reservas.api.gateway.database.jpa.mapper;

import api.reservas.api.domain.Restaurante;
import api.reservas.api.gateway.database.jpa.entity.EnderecoEntity;
import api.reservas.api.gateway.database.jpa.entity.RestauranteEntity;
import api.reservas.api.gateway.database.jpa.entity.TipoCozinhaEntity;

public class RestauranteEntityMapper {

    // remove?
    public RestauranteEntity mapToEntity(Long id, Restaurante restaurante) {
        return mapToEntity(id, restaurante, true);
    }

    // remove?
    public RestauranteEntity mapToEntity(Long idRestaurante, Restaurante restaurante, boolean ativo, EnderecoEntity enderecoEntity, TipoCozinhaEntity tipoCozinhaEntity) {
        return new RestauranteEntity(
                idRestaurante,
                restaurante.cnpj(),
                restaurante.nome(),
                restaurante.horaAbertura(),
                restaurante.horaFechamento(),
                restaurante.capacidade(),
                ativo,
                enderecoEntity,
                tipoCozinhaEntity
        );
    }

    public RestauranteEntity mapToEntity(Restaurante restaurante, EnderecoEntity enderecoEntity, TipoCozinhaEntity tipoCozinhaEntity) {
        var restauranteEntity = mapToEntity(restaurante);
        restauranteEntity.setEndereco(enderecoEntity);
        restauranteEntity.setTipoCozinha(tipoCozinhaEntity);

        return restauranteEntity;
    }

    public RestauranteEntity mapToEntity(Long idRestaurante, Restaurante restaurante, boolean ativo) {
        return new RestauranteEntity(
                idRestaurante,
                restaurante.cnpj(),
                restaurante.nome(),
                restaurante.horaAbertura(),
                restaurante.horaFechamento(),
                restaurante.capacidade(),
                ativo,
                null,
                null
        );
    }

    public RestauranteEntity mapToEntity(Restaurante restaurante) {
        return mapToEntity(null, restaurante, true);
    }

}
