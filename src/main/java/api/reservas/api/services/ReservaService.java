package api.reservas.api.services;

import api.reservas.api.dto.*;

import api.reservas.api.entitys.Reserva;
import api.reservas.api.entitys.Restaurante;
import api.reservas.api.entitys.Usuario;
import api.reservas.api.repository.ReservaRepository;

import api.reservas.api.repository.RestauranteRepository;
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

    @Autowired
    private RestauranteRepository resturanteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Long criarReserva(ReservaDTO reservaDTO) {

        var idRestaurante = reservaDTO.idRestaurante();
        var idUsuario = reservaDTO.idUsuario();

        var restaurante = new Restaurante();
        restaurante.setId(idRestaurante);

        var usuario = new Usuario();
        usuario.setId(idUsuario);

        var reserva = new Reserva();

        reserva.setRestaurante(restaurante);
        reserva.setUsuario(usuario);
        reserva.setQuantidade(reservaDTO.quantidade());
        reserva.setHorario(reservaDTO.horario());
        reserva.setAtivo(true);

        var reservaSalva = reservaRepository.save(reserva);
        return reservaSalva.getId();

    }

    @Transactional
    public Page<ReservaDTO> listarTodos(Pageable paginacao) {
        return reservaRepository.findAll(paginacao).map(ReservaDTO::new);
    }

    @Transactional
    public void cancelarReserva(Long id) {
        Optional<Reserva> reserva = reservaRepository.findById(id);
        reserva.ifPresent(value -> value.setAtivo(false));
    }

}