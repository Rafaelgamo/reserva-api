package api.reservas.api.gateway.database.jpa;

import api.reservas.api.domain.Restaurante;
import api.reservas.api.gateway.RestauranteGateway;
import api.reservas.api.gateway.database.jpa.entity.EnderecoEntity;
import api.reservas.api.gateway.database.jpa.entity.TipoCozinhaEntity;
import api.reservas.api.gateway.database.jpa.mapper.RestauranteMapper;
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

    private final RestauranteMapper restauranteMapper = new RestauranteMapper();

    public RestauranteJpaGateway(RestauranteRepository restauranteRepository, TipoCozinhaRepository tipoCozinhaRepository, EnderecoRepository enderecoRepository) {
        this.restauranteRepository = restauranteRepository;
        this.tipoCozinhaRepository = tipoCozinhaRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    public Long cadastrarRestaurante(Restaurante domainRestaurante) {
        logger.info("Cadastrando restaurante: nome={}", domainRestaurante.nome());

        var enderecoDomain = domainRestaurante.endereco();
        var enderecoEntity = buscarOuCriarEndereco(enderecoDomain.cep(), enderecoDomain.numero());

        var tipoCozinhaEntity = buscarOuCriarTipoCozinha(domainRestaurante.tipoCozinha());

        var entityRestaurante = restauranteMapper.mapToEntity(domainRestaurante, enderecoEntity, tipoCozinhaEntity);
        return restauranteRepository.save(entityRestaurante).getId();
    }

    @Override
    public boolean existePorCnpj(String cnpj) {
        return restauranteRepository.existsByCnpj(cnpj);
    }

    private TipoCozinhaEntity buscarOuCriarTipoCozinha(String nomeTipoCozinha) {
        var lowerNome = nomeTipoCozinha.toLowerCase();
        var tipoCozinha = tipoCozinhaRepository.findByNome(lowerNome);

        if (tipoCozinha == null) {
            var newTipoCozinha = new TipoCozinhaEntity(null, lowerNome);
            var savedTipoCozinha = tipoCozinhaRepository.save(newTipoCozinha);
            return savedTipoCozinha;
        }

        return tipoCozinha;
    }

    private EnderecoEntity buscarOuCriarEndereco(String cep, String numero) {
        var endereco = enderecoRepository.findByCepAndNumero(cep, numero);

        if (endereco == null) {
            var newEndereco = new EnderecoEntity(null, cep, numero);
            var savedEndereco = enderecoRepository.save(newEndereco);
            return savedEndereco;
        }

        return endereco;
    }

}
