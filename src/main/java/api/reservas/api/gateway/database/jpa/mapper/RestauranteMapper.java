package api.reservas.api.gateway.database.jpa.mapper;

import api.reservas.api.domain.restaurante.Endereco;
import api.reservas.api.domain.restaurante.Restaurante;
import api.reservas.api.gateway.database.jpa.entity.EnderecoEntity;
import api.reservas.api.gateway.database.jpa.entity.RestauranteEntity;
import api.reservas.api.gateway.database.jpa.entity.TipoCozinhaEntity;

public class RestauranteMapper {

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
                restaurante.capacidadeEmMesas(),
                ativo,
                null,
                null
        );
    }

    public RestauranteEntity mapToEntity(Restaurante restaurante) {
        return mapToEntity(null, restaurante, true);
    }

    public Restaurante mapToDomain(RestauranteEntity restauranteEntity) {
        var entityEndereco = restauranteEntity.getEndereco();
        var endereco = new Endereco(entityEndereco.getCep(), entityEndereco.getNumero());

        var restaurante = new Restaurante(
                restauranteEntity.getNome(),
                restauranteEntity.getCnpj(),
                endereco,
                restauranteEntity.getTipoCozinha().getNome(),
                restauranteEntity.getHoraAbertura(),
                restauranteEntity.getHoraFechamento(),
                restauranteEntity.getCapacidadeEmMesas()
        );
        return restaurante;
    }
}
