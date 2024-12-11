package api.reservas.api.repository_remove;

import api.reservas.api.entitys_remove.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    Page<Reserva> findAllByMesaOcupadaTrue(Pageable paginacao);

}