package api.reservas.api.usecase.restaurantes;

import api.reservas.api.domain.paging.PagedResult;
import api.reservas.api.domain.paging.PagingInfo;
import api.reservas.api.domain.restaurante.Restaurante;
import api.reservas.api.gateway.RestauranteGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BuscarRestaurantesUseCase {

    private static final Logger logger = LoggerFactory.getLogger(BuscarRestaurantesUseCase.class);

    private final RestauranteGateway restauranteGateway;

    public BuscarRestaurantesUseCase(RestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    public Restaurante buscarPorCnpj(String cnpj) {
        return restauranteGateway.buscarPorCnpj(cnpj);
    }

    public PagedResult<Restaurante> filtrarPorNome(String nome, PagingInfo pagingInfo) {
        return restauranteGateway.filtrarPorNomeAproximado(nome, pagingInfo);
    }

    public PagedResult<Restaurante> filtrarPorCep(String cep, PagingInfo pagingInfo) {
        return restauranteGateway.filtrarPorCep(cep, pagingInfo);
    }

    public PagedResult<Restaurante> filtrarPorTipoCozinha(String tipoCozinha, PagingInfo pagingInfo) {
        return restauranteGateway.filtrarPorTipoCozinha(tipoCozinha, pagingInfo);
    }

    public PagedResult<Restaurante> filtrarAbertosNoMomento(PagingInfo pagingInfo) {
        return restauranteGateway.filtrarAbertosNoMomento(pagingInfo);
    }
}
