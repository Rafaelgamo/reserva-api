package api.reservas.api.repository;


import api.reservas.api.entitys.Reserva;
import api.reservas.api.entitys.Usuario;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservaRepository extends JpaRepository< Reserva, Long> {

    @Bean
    Optional<Reserva> findById(Long id);

}