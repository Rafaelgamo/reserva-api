package api.reservas.api.gateway.database.jpa.repository;

import api.reservas.api.gateway.database.jpa.entity.AvaliacaoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoRepository extends JpaRepository<AvaliacaoEntity, Long>  {

    @Query("from AvaliacaoEntity as A " +
            "where A.reserva.restaurante.cnpj = ?1")
    Page<AvaliacaoEntity> buscarPorCnpj(String cnpj, Pageable pageable);
}
