package api.reservas.api.gateway;

import api.reservas.api.domain.restaurante.Restaurante;
import api.reservas.api.usecase.dto.AlterarRestauranteDTO;

public interface RestauranteGateway {

    Restaurante buscarPorCnpj(String cnpj);

    Long cadastrar(Restaurante domainRestaurante);

    boolean existePorCnpj(String cnpj);

    void excluir(String cnpj);

    void alterarPorCnpj(String cnpj, AlterarRestauranteDTO alterarRestauranteDTO);
}
