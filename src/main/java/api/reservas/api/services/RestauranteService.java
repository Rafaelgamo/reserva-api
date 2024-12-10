package api.reservas.api.services;

import api.reservas.api.dto.RestauranteDTO;
import api.reservas.api.entitys.Restaurante;
import api.reservas.api.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class RestauranteService {

    @Autowired
    private final RestauranteRepository restauranteRepository;

    public RestauranteService(RestauranteRepository restauranteRepository){ this.restauranteRepository = restauranteRepository;}

    @Transactional
    public Long cadastroRestaurante(RestauranteDTO dados){

        var restaurante = new Restaurante();

        restaurante.setNome(dados.nome());
        restaurante.setEndereco(dados.endereco());
        restaurante.setTipodecozinha(dados.tipodecozinha());
        restaurante.setFuncionamento(dados.funcionamento());
        restaurante.setCapacidade(dados.capacidade());
        restaurante.setAtivo(true);

        var restauranteSalvo = restauranteRepository.save(restaurante);

        return restauranteSalvo.getId();
    }

    @Transactional
    public Page<RestauranteDTO> listarAtivos(Pageable paginacao) {
        return restauranteRepository.findAllByAtivoTrue(paginacao).map(RestauranteDTO::new);
    }

    @Transactional
    public void removerRestaurante(Long id) {
        Optional<Restaurante> restaurante = restauranteRepository.findById(id);
        restaurante.ifPresent(value -> value.setAtivo(false));
    }

    @Transactional
    public List<Restaurante> buscarPorNome(String nome){
        var restaurante = restauranteRepository.findByNome(nome);
        return restaurante;
    }

    @Transactional
    public Optional<Restaurante> buscarPorEndereco(String endereco){
        var restaurante = restauranteRepository.findByEndereco(endereco);
        return restaurante;
    }
    @Transactional
    public List<Restaurante> buscarPorCozinha(String tipodecozinha){
        var restaurante = restauranteRepository.findByTipodecozinha(tipodecozinha);
        return restaurante;
       
    }
}