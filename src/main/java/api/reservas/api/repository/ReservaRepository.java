package api.reservas.api.repository;


import api.reservas.api.entitys.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository< Reserva, Long> {




}