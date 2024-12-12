package api.reservas.api.usecase.restaurantes;

import api.reservas.api.exception.RecursoNaoEncontradoException;
import api.reservas.api.exception.ValidacaoException;
import api.reservas.api.gateway.RestauranteGateway;
import api.reservas.api.gateway.database.jpa.entity.RestauranteEntity;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ExcluirRestauranteUseCase {

    private static final Logger logger = LoggerFactory.getLogger(ExcluirRestauranteUseCase.class);

    private final RestauranteGateway restauranteGateway;

    public ExcluirRestauranteUseCase(RestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    @Transactional
    public void excluirRestaurante(String cnpj) {
        cnpj = cnpj.trim().replaceAll("\\D", "");

        if (cnpj.length() != 14) {
            logger.warn("CNPJ invalido: {}", cnpj);
            throw new ValidacaoException("CNPJ deve conter 14 digitos");
        }

        var cnpjCadastrado = restauranteGateway.existePorCnpj(cnpj);
        if (!cnpjCadastrado) {
            logger.warn("CNPJ não encontrado em restaurantes: {}", cnpj);
            throw new RecursoNaoEncontradoException(RestauranteEntity.class, "cnpj", cnpj);
        }

        restauranteGateway.excluir(cnpj);
    }

}
