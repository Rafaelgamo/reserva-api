package api.reservas.api.services;

import api.reservas.api.dto.RestauranteDTO;
import api.reservas.api.entitys.Restaurante;
import api.reservas.api.repository.RestauranteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RestauranteService {


    private final RestauranteRepository restauranteRepository;

    public RestauranteService(RestauranteRepository restauranteRepository){ this.restauranteRepository = restauranteRepository;}

    @Transactional
    public long cadastroRestaurante(RestauranteDTO dados){
        var nome = dados.nome();
        var endereco = dados.endereco();
        var tipodecomida = dados.tipodecozinha();
        var funcionamento = dados.funcionamento();
        var capacidade = dados.capacidade();
        var restaurante = new Restaurante();

        restaurante.setNome(dados.nome());
        restaurante.setEndereco(dados.endereco());
        restaurante.setTipodecozinha(dados.tipodecozinha());
        restaurante.setFuncionamento(dados.funcionamento());
        restaurante.setCapacidade(dados.capacidade());

        var restauranteSalvo = restauranteRepository.save(restaurante);
        return restauranteSalvo.getId();
    }

    @Transactional
    public Page<RestauranteDTO> listarTodos(Pageable paginacao) {
        return restauranteRepository.findAll(paginacao).map(RestauranteDTO::new);
    }

    @Transactional
    public Optional<RestauranteDTO> findById(Long id) {
        return restauranteRepository.findById(id).map(RestauranteDTO::new);
    }

}




