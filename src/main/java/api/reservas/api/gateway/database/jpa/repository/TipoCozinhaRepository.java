package api.reservas.api.gateway.database.jpa.repository;


import api.reservas.api.gateway.database.jpa.entity.TipoCozinhaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoCozinhaRepository extends JpaRepository<TipoCozinhaEntity, Long> {
    TipoCozinhaEntity findByNome(String nome);
}
