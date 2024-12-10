package api.reservas.api.services;

import api.reservas.api.dto.ReservaDTO;
import api.reservas.api.entitys.*;
import api.reservas.api.repository.AvaliacaoRepository;
import api.reservas.api.repository.ReservaRepository;
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

    public ReservaService(ReservaRepository reservaRepository, AvaliacaoRepository avaliacaoRepository) {
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

    // registrar liberação de mesa

    //avaliacao

    }

