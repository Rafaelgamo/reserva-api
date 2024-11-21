package api.reservas.api.repository;

import api.reservas.api.entitys.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RestauranteRepository  extends JpaRepository<Restaurante, Long> {


}








