package api.reservas.api.gateway;

import api.reservas.api.domain.Restaurante;

public interface RestauranteGateway {
    Long cadastrarRestaurante(Restaurante domainRestaurante);

    boolean existePorCnpj(String cnpj);
}
