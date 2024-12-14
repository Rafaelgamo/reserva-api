package api.reservas.api.gateway;

import api.reservas.api.domain.Restaurante;
import api.reservas.api.usecase.dto.AlterarRestauranteDTO;

public interface RestauranteGateway {
    Long cadastrar(Restaurante domainRestaurante);

    boolean existePorCnpj(String cnpj);

    void excluir(String cnpj);

    void alterarPorCnpj(String cnpj, AlterarRestauranteDTO alterarRestauranteDTO);
}
