package api.reservas.api.gateway;

import api.reservas.api.domain.paging.PagedResult;
import api.reservas.api.domain.paging.PagingInfo;
import api.reservas.api.domain.restaurante.Restaurante;
import api.reservas.api.usecase.dto.AlterarRestauranteDTO;

public interface RestauranteGateway {

    Restaurante buscarPorCnpj(String cnpj);
    PagedResult<Restaurante> filtrarAbertosNoMomento(PagingInfo pagingInfo);
    PagedResult<Restaurante> filtrarPorNomeAproximado(String nome, PagingInfo pagingInfo);
    PagedResult<Restaurante> filtrarPorCep(String cep, PagingInfo pagingInfo);
    PagedResult<Restaurante> filtrarPorTipoCozinha(String tipoCozinha, PagingInfo pagingInfo);

    Long cadastrar(Restaurante domainRestaurante);

    boolean existePorCnpj(String cnpj);

    void excluir(String cnpj);

    void alterarPorCnpj(String cnpj, AlterarRestauranteDTO alterarRestauranteDTO);
}
