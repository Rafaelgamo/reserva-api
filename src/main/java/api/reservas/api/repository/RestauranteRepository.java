package api.reservas.api.repository;

import api.reservas.api.entitys.Restaurante;
import api.reservas.api.entitys.Usuario;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RestauranteRepository  extends JpaRepository<Restaurante, Long> {

    @Bean
    Optional<Restaurante> findById(Long id);

    boolean existsByEndereco(String endereco);

}

