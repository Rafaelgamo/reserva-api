package api.reservas.api.gateway.database.jpa.repository;

import api.reservas.api.domain.reserva.Reserva;
import api.reservas.api.gateway.database.jpa.entity.ReservaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {

    @Query("select count(*)" +
            "from ReservaEntity as RV " +
            "where RV.restaurante.cnpj = ?1" +
            " AND DAY(RV.data) = DAY(?2)")
    Integer contarReservasPorDia(String cnpj, LocalDateTime dataRequisitada);

    @Query("from ReservaEntity as RV " +
            "where RV.restaurante.cnpj = ?1")
    Page<Reserva> buscarPorCnpj(String cnpj, PageRequest pageRequest);
}
