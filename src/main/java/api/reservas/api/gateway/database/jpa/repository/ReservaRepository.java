package api.reservas.api.gateway.database.jpa.repository;

import api.reservas.api.gateway.database.jpa.entity.ReservaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {

    @Query("select count(*) " +
            "from ReservaEntity as RV " +
            "where RV.restaurante.cnpj = ?1" +
            "   and RV.status = 'AGENDADA'" +
            "   and RV.data >= current timestamp")
    Integer contarReservasNaoConcluidas(String cnpj);

    @Query("from ReservaEntity as RV " +
            "where RV.restaurante.cnpj = ?1")
    Page<ReservaEntity> buscarPorCnpj(String cnpj, PageRequest pageRequest);
}
