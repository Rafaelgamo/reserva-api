package api.reservas.api.gateway.database.jpa;

import api.reservas.api.domain.paging.PagedResult;
import api.reservas.api.domain.paging.PagingInfo;
import api.reservas.api.domain.restaurante.Restaurante;
import api.reservas.api.exception.ErroInternoException;
import api.reservas.api.exception.RecursoNaoEncontradoException;
import api.reservas.api.exception.ValidacaoException;
import api.reservas.api.gateway.RestauranteGateway;
import api.reservas.api.gateway.database.jpa.entity.EnderecoEntity;
import api.reservas.api.gateway.database.jpa.entity.RestauranteEntity;
import api.reservas.api.gateway.database.jpa.entity.TipoCozinhaEntity;
import api.reservas.api.gateway.database.jpa.mapper.RestauranteMapper;
import api.reservas.api.gateway.database.jpa.repository.EnderecoRepository;
import api.reservas.api.gateway.database.jpa.repository.RestauranteRepository;
import api.reservas.api.gateway.database.jpa.repository.TipoCozinhaRepository;
import api.reservas.api.usecase.dto.AlterarRestauranteDTO;
import api.reservas.api.util.ValidadorFormatoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
    public Restaurante buscarPorCnpj(String cnpj) {
        Optional<RestauranteEntity> optRestaurante = restauranteRepository.findByCnpj(cnpj);
        if (optRestaurante.isEmpty()) {
            return null;
        }

        var restauranteEntity = optRestaurante.get();
        var restaurante = restauranteMapper.mapToDomain(restauranteEntity);
        return restaurante;
    }

    @Override
    public PagedResult<Restaurante> filtrarAbertosNoMomento(PagingInfo pagingInfo) {
        var entitties = restauranteRepository.findAllCurrentlyOpen(pagingInfo.toPageRequest());
        var domainRestaurantes = entitties.map(restauranteMapper::mapToDomain);
        return PagedResult.of(domainRestaurantes, pagingInfo);
    }

    @Override
    public PagedResult<Restaurante> filtrarPorNomeAproximado(String nome, PagingInfo pagingInfo) {
        var pagedFiltrado = restauranteRepository.findAllByNomeLike(nome.toLowerCase(), pagingInfo.toPageRequest());
        var domainRestaurantes = pagedFiltrado.map(restauranteMapper::mapToDomain);
        return PagedResult.of(domainRestaurantes, pagingInfo);
    }

    @Override
    public PagedResult<Restaurante> filtrarPorCep(String cep, PagingInfo pagingInfo) {
        var pagedFiltrado = restauranteRepository.findAllByCep(cep, pagingInfo.toPageRequest());
        var domainRestaurantes = pagedFiltrado.map(restauranteMapper::mapToDomain);
        return PagedResult.of(domainRestaurantes, pagingInfo);
    }

    @Override
    public PagedResult<Restaurante> filtrarPorTipoCozinha(String tipoCozinha, PagingInfo pagingInfo) {
        var pagedFiltrado = restauranteRepository.findAllByTipoCozinha(tipoCozinha.toLowerCase(), pagingInfo.toPageRequest());
        var domainRestaurantes = pagedFiltrado.map(restauranteMapper::mapToDomain);
        return PagedResult.of(domainRestaurantes, pagingInfo);
    }

    @Override
    public Long cadastrar(Restaurante domainRestaurante) {
        logger.info("Cadastrando restaurante: nome={}, cnpj={}", domainRestaurante.nome(), domainRestaurante.cnpj());

        var enderecoDomain = domainRestaurante.endereco();
        var enderecoEntity = buscarOuCriarEndereco(enderecoDomain.cep(), enderecoDomain.numero());

        var tipoCozinhaEntity = buscarOuCriarTipoCozinha(domainRestaurante.tipoCozinha());

        var entityRestaurante = restauranteMapper.mapToEntity(domainRestaurante, enderecoEntity, tipoCozinhaEntity);

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

    @Override
    public void alterarPorCnpj(String cnpj, AlterarRestauranteDTO alterarRestauranteDTO) {
        logger.info("Alterando restaurante: cnpj={}", cnpj);

        if (cnpj == null) {
            logger.warn("CNPJ tem que ser informado para alterar um restaurante.");
            throw new ValidacaoException("CNPJ tem que ser informado para alterar um restaurante.");
        }

        var optionalRestaurante = restauranteRepository.findByCnpj(cnpj);
        if (optionalRestaurante.isEmpty()) {
            throw new RecursoNaoEncontradoException(RestauranteEntity.class, "cnpj", cnpj);
        }

        var restauranteEntity = optionalRestaurante.get();

        var novoNome = alterarRestauranteDTO.nome();
        if (novoNome != null) {
            if (novoNome.trim().isBlank()) {
                throw new ValidacaoException("Novo nome do restaurante não pode ser vazio");
            }

            restauranteEntity.setNome(novoNome);
        }

        var novoTipoCozinha = alterarRestauranteDTO.tipoCozinha();
        if (novoTipoCozinha != null) {
            if (novoTipoCozinha.trim().isBlank()) {
                throw new ValidacaoException("Novo tipo de cozinha não pode ser vazio");
            }

            var tipoCozinhaEntity = buscarOuCriarTipoCozinha(novoTipoCozinha);
            restauranteEntity.setTipoCozinha(tipoCozinhaEntity);
        }

        var novoEndereco = alterarRestauranteDTO.endereco();
        if (novoEndereco != null) {
            var cep = novoEndereco.cep();
            if (!ValidadorFormatoUtil.formatoCepValido(cep)) {
                throw new ValidacaoException("Novo cep não é válido");
            }

            var enderecoEntity = buscarOuCriarEndereco(cep, novoEndereco.numero());
            restauranteEntity.setEndereco(enderecoEntity);
        }

        var novaHoraAbertura = alterarRestauranteDTO.horaAbertura();
        if (novaHoraAbertura != null) {
            if (novaHoraAbertura < 0 || novaHoraAbertura > 24) {
                throw new ValidacaoException("Hora de abertura não pode ser negativa ou exceder 24");
            }

            restauranteEntity.setHoraAbertura(novaHoraAbertura);
        }

        var novaHoraFechamento = alterarRestauranteDTO.horaFechamento();
        if (novaHoraFechamento != null) {
            if (novaHoraFechamento < 0 || novaHoraFechamento > 24) {
                throw new ValidacaoException("Hora de abertura não pode ser negativa ou exceder 24");
            }

            restauranteEntity.setHoraFechamento(novaHoraFechamento);
        }

        var novaCapacidadeEmMesas = alterarRestauranteDTO.capacidadeEmMesas();
        if (novaCapacidadeEmMesas != null) {
            if (novaCapacidadeEmMesas < 0) {
                throw new ValidacaoException("Capacidade em mesas negativo");
            }

            restauranteEntity.setCapacidadeEmMesas(novaCapacidadeEmMesas);
        }
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
