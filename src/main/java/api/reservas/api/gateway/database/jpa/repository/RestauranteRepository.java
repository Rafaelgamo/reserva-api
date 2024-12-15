package api.reservas.api.gateway.database.jpa.repository;

import api.reservas.api.gateway.database.jpa.entity.RestauranteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RestauranteRepository extends JpaRepository<RestauranteEntity, Long> {

    boolean existsByCnpj(String cnpj);

    void deleteByCnpj(String cnpj);

    Optional<RestauranteEntity> findByCnpj(String cnpj);

    Page<RestauranteEntity> findAllByNomeLike(String nome, Pageable pageable);

    @Query("from RestauranteEntity as R " +
            "where R.endereco.cep = ?1")
    Page<RestauranteEntity> findAllByCep(String cep, PageRequest pageRequest);

    @Query("from RestauranteEntity as R " +
            "where R.tipoCozinha.nome = ?1")
    Page<RestauranteEntity> findAllByTipoCozinha(String tipoCozinha, PageRequest pageRequest);
}

