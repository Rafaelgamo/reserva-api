package api.reservas.api.repository;

import api.reservas.api.entitys.Restaurante;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository  extends JpaRepository<Restaurante, Long> {


    Page<Restaurante> findAllByAtivoTrue(Pageable paginacao);

    @Bean
    Optional<Restaurante> findById(Long id);

    @Bean
    List<Restaurante> findByNome(String nome);

    @Bean
    Optional<Restaurante> findByEndereco(String endereco);

    @Bean
    List<Restaurante> findByTipodecozinha(String tipodecozinha);


}

