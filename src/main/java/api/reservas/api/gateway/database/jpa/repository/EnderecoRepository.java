package api.reservas.api.gateway.database.jpa.repository;


import api.reservas.api.gateway.database.jpa.entity.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {

    EnderecoEntity findByCepAndNumero(String cep, String numero);
}
