package api.reservas.api.services_remove;

import api.reservas.api.dto_remove.ReservaDTO;
import api.reservas.api.entitys_remove.Reserva;
import api.reservas.api.entitys_remove.Usuario;
import api.reservas.api.entitys_remove.Vaga;
import api.reservas.api.repository_remove.ReservaRepository_Remove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository_Remove reservaRepositoryRemove;

    public ReservaService(ReservaRepository_Remove reservaRepositoryRemove) {
        this.reservaRepositoryRemove = reservaRepositoryRemove;
    }

    //criar reserva
    @Transactional
    public Long criarReserva(ReservaDTO reservaDTO){

        var idUsuario = reservaDTO.usuario();
        var usuario = new Usuario();
        usuario.setId(idUsuario);

        var idVaga = reservaDTO.vaga();
        var vaga = new Vaga();
        vaga.setId(idVaga);

        var reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setVaga(vaga);
        reserva.setMesaOcupada(true);

        var salvarReserva = reservaRepositoryRemove.save(reserva);
        return salvarReserva.getId();

    }

    @Transactional
    public Page<ReservaDTO> listar(Pageable paginacao) {
        return reservaRepositoryRemove.findAllByMesaOcupadaTrue(paginacao).map(ReservaDTO::new);
    }

    //cancelar reserva
    @Transactional
    public void removerReserva(Long id) {
        Optional<Reserva> cancelarReserva = reservaRepositoryRemove.findById(id);
        cancelarReserva.ifPresent(value -> value.setMesaOcupada(false));
    }

}

