package api.reservas.api.usecase.restaurantes;

import api.reservas.api.domain.Endereco;
import api.reservas.api.domain.Restaurante;
import api.reservas.api.exception.RecursoJaCadastradoException;
import api.reservas.api.gateway.RestauranteGateway;
import api.reservas.api.gateway.database.jpa.entity.RestauranteEntity;
import api.reservas.api.usecase.dto.RestauranteDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CadastrarRestauranteUseCase {

    private final RestauranteGateway restauranteGateway;

    public CadastrarRestauranteUseCase(RestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    @Transactional
    public Long cadastrarRestaurante(RestauranteDTO restauranteDTO) {
        var cnpj = restauranteDTO.cnpj();
        var cnpjCadastrado = restauranteGateway.existePorCnpj(cnpj);
        if (cnpjCadastrado) {
            throw new RecursoJaCadastradoException(RestauranteEntity.class, "cnpj", cnpj);
        }

        var endereco = new Endereco(restauranteDTO.cep(), restauranteDTO.numeroEndereco());

        var domainRestaurante = new Restaurante(
                restauranteDTO.nome(),
                cnpj,
                endereco,
                restauranteDTO.tipoCozinha(),
                restauranteDTO.horaAbertura(),
                restauranteDTO.horaFechamento(),
                restauranteDTO.capacidade()
        );

        var restauranteId = restauranteGateway.cadastrarRestaurante(domainRestaurante);
        return restauranteId;
    }

}
