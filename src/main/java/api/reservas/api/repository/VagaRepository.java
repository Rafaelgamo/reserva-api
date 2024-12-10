package api.reservas.api.repository;

import api.reservas.api.entitys.Usuario;
import api.reservas.api.entitys.Vaga;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {

    @Bean
    Optional<Vaga> findById(Long id);
}





