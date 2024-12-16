package api.reservas.api.gateway.database.jpa.repository;

import api.reservas.api.gateway.database.jpa.entity.RestauranteEntity;
import org.springframework.data.domain.Page;
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

    @Query("from RestauranteEntity as R " +
            "where lower(R.nome) like ?1")
    Page<RestauranteEntity> findAllByNomeLike(String nome, Pageable pageable);

    @Query("from RestauranteEntity as R " +
            "where R.endereco.cep = ?1")
    Page<RestauranteEntity> findAllByCep(String cep, Pageable pageable);

    @Query("from RestauranteEntity as R " +
            "where lower(R.tipoCozinha.nome) = lower(?1)")
    Page<RestauranteEntity> findAllByTipoCozinha(String tipoCozinha, Pageable pageable);

    @Query("from RestauranteEntity as R " +
            "where hour(CURRENT_TIME()) between R.horaAbertura and R.horaFechamento")
    Page<RestauranteEntity> findAllCurrentlyOpen(Pageable pageable);
}

