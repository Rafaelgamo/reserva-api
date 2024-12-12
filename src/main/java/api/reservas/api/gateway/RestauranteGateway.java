package api.reservas.api.gateway;

import api.reservas.api.domain.Restaurante;

public interface RestauranteGateway {
    Long cadastrar(Restaurante domainRestaurante);

    boolean existePorCnpj(String cnpj);

    void excluir(String cnpj);
}
