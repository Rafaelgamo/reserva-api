package api.reservas.api.usecase.restaurantes;

import api.reservas.api.exception.RecursoNaoEncontradoException;
import api.reservas.api.exception.ValidacaoException;
import api.reservas.api.gateway.RestauranteGateway;
import api.reservas.api.gateway.database.jpa.entity.RestauranteEntity;
import api.reservas.api.usecase.dto.AlterarRestauranteDTO;
import api.reservas.api.util.ValidadorFormatoUtil;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AlterarRestauranteUseCase {

    private static final Logger logger = LoggerFactory.getLogger(AlterarRestauranteUseCase.class);

    private final RestauranteGateway restauranteGateway;

    public AlterarRestauranteUseCase(RestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    @Transactional
    public void alterarRestaurante(String cnpj, AlterarRestauranteDTO alterarRestauranteDTO) {
        if (!ValidadorFormatoUtil.formatoCnpjValido(cnpj)) {
            logger.warn("CNPJ tem que ser válido para alterar um restaurante.");
            throw new ValidacaoException("CNPJ tem que ser válido para alterar um restaurante.");
        }

        var cnpjCadastrado = restauranteGateway.existePorCnpj(cnpj);
        if (!cnpjCadastrado) {
            logger.warn("CNPJ não cadastrado em restaurantes: {}", cnpj);
            throw new RecursoNaoEncontradoException(RestauranteEntity.class, "cnpj", cnpj);
        }

        restauranteGateway.alterarPorCnpj(cnpj, alterarRestauranteDTO);
    }

}
