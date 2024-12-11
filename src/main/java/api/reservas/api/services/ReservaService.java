package api.reservas.api.services;

import api.reservas.api.dto.ReservaDTO;
import api.reservas.api.entitys.Reserva;
import api.reservas.api.entitys.Usuario;
import api.reservas.api.gateway.database.jpa.entity.RestauranteEntity;
import api.reservas.api.gateway.database.jpa.repository.RestauranteRepository;
import api.reservas.api.repository.ReservaRepository;
import api.reservas.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
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

        var salvarReserva = reservaRepository.save(reserva);
        return salvarReserva.getId();

    }

    @Transactional
    public Page<ReservaDTO> listar(Pageable paginacao) {
        return reservaRepository.findAllByMesaOcupadaTrue(paginacao).map(ReservaDTO::new);
    }

    //cancelar reserva
    @Transactional
    public void removerReserva(Long id) {
        Optional<Reserva> cancelarReserva = reservaRepository.findById(id);
        cancelarReserva.ifPresent(value -> value.setMesaOcupada(false));
    }

}

