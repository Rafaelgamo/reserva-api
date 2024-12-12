package api.reservas.api.gateway.database.jpa;

import api.reservas.api.domain.Restaurante;
import api.reservas.api.exception.ErroInternoException;
import api.reservas.api.gateway.RestauranteGateway;
import api.reservas.api.gateway.database.jpa.entity.EnderecoEntity;
import api.reservas.api.gateway.database.jpa.entity.TipoCozinhaEntity;
import api.reservas.api.gateway.database.jpa.mapper.RestauranteEntityMapper;
import api.reservas.api.gateway.database.jpa.repository.EnderecoRepository;
import api.reservas.api.gateway.database.jpa.repository.RestauranteRepository;
import api.reservas.api.gateway.database.jpa.repository.TipoCozinhaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RestauranteJpaGateway implements RestauranteGateway {

    private static final Logger logger = LoggerFactory.getLogger(RestauranteJpaGateway.class);

    private final RestauranteRepository restauranteRepository;
    private final TipoCozinhaRepository tipoCozinhaRepository;
    private final EnderecoRepository enderecoRepository;

    private final RestauranteEntityMapper restauranteEntityMapper = new RestauranteEntityMapper();

    public RestauranteJpaGateway(RestauranteRepository restauranteRepository, TipoCozinhaRepository tipoCozinhaRepository, EnderecoRepository enderecoRepository) {
        this.restauranteRepository = restauranteRepository;
        this.tipoCozinhaRepository = tipoCozinhaRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    public Long cadastrar(Restaurante domainRestaurante) {
        logger.info("Cadastrando restaurante: nome={}, cnpj={}", domainRestaurante.nome(), domainRestaurante.cnpj());

        var enderecoDomain = domainRestaurante.endereco();
        var enderecoEntity = buscarOuCriarEndereco(enderecoDomain.cep(), enderecoDomain.numero());

        var tipoCozinhaEntity = buscarOuCriarTipoCozinha(domainRestaurante.tipoCozinha());

        var entityRestaurante = restauranteEntityMapper.mapToEntity(domainRestaurante, enderecoEntity, tipoCozinhaEntity);

        Long restauranteId;
        try {
            restauranteId = restauranteRepository.save(entityRestaurante).getId();
        } catch (Exception e) {
            logger.error("Erro cadastrando restaurante: nome={}, cnpj={}", domainRestaurante.nome(), domainRestaurante.cnpj());
            throw new ErroInternoException(e.getLocalizedMessage());
        }

        return restauranteId;
    }

    @Override
    public boolean existePorCnpj(String cnpj) {
        logger.info("Checando existencia por cnpj: cnpj={}", cnpj);
        return restauranteRepository.existsByCnpj(cnpj);
    }

    @Override
    public void excluir(String cnpj) {
        logger.info("Excluindo restaurante: cnpj={}", cnpj);
        restauranteRepository.deleteByCnpj(cnpj);
    }

    private TipoCozinhaEntity buscarOuCriarTipoCozinha(String nomeTipoCozinha) {
        var lowerNome = nomeTipoCozinha.toLowerCase();
        var tipoCozinha = tipoCozinhaRepository.findByNome(lowerNome);

        if (tipoCozinha == null) {
            logger.info("Criando tipo cozinha: nome={}", lowerNome);
            var newTipoCozinha = new TipoCozinhaEntity(null, lowerNome);
            var savedTipoCozinha = tipoCozinhaRepository.save(newTipoCozinha);
            return savedTipoCozinha;
        }

        return tipoCozinha;
    }

    private EnderecoEntity buscarOuCriarEndereco(String cep, String numero) {
        var endereco = enderecoRepository.findByCepAndNumero(cep, numero);

        if (endereco == null) {
            logger.info("Criando endereço: cep={}, numero={}", cep, numero);
            var newEndereco = new EnderecoEntity(null, cep, numero);
            var savedEndereco = enderecoRepository.save(newEndereco);
            return savedEndereco;
        }

        return endereco;
    }

}
