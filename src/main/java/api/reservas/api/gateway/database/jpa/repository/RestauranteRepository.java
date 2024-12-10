package api.reservas.api.gateway.database.jpa.repository;

import api.reservas.api.gateway.database.jpa.entity.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RestauranteRepository extends JpaRepository<RestauranteEntity, Long> {

    boolean existsByCnpj(String cnpj);

}

