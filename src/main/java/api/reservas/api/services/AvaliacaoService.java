package api.reservas.api.services;

import api.reservas.api.dto.AvaliacaoDTO;
import api.reservas.api.dto.UsuarioDTO;
import api.reservas.api.entitys.*;
import api.reservas.api.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Transactional
    public Long criarAvaliacao(AvaliacaoDTO avaliacaoDTO) {

        var notaAvaliacao = avaliacaoDTO.notaAvaliacao();
        var avaliacao = new Avaliacao();
        avaliacao.setNotaAvaliacao(notaAvaliacao);

        var  idReserva = avaliacaoDTO.idReserva();
        var reserva = new Reserva();
        reserva.setId(idReserva);

        var idRestaurante = avaliacaoDTO.idRestaurante();
        var restaurante = new Restaurante();
        restaurante.setId(idRestaurante);

        var avaliar = new Avaliacao();
        avaliar.setNotaAvaliacao(avaliacaoDTO.notaAvaliacao());
        avaliar.setRestaurante(restaurante);
        avaliar.setReserva(reserva);

        avaliar.setComentario(avaliacaoDTO.comentario());

        var salvaAvaliacao = avaliacaoRepository.save(avaliacao);
        return salvaAvaliacao.getId();
    }

    @Transactional
    public Page<AvaliacaoDTO> listar(Pageable paginacao) {
        return avaliacaoRepository.findAll(paginacao).map(AvaliacaoDTO::new);
    }
    }



