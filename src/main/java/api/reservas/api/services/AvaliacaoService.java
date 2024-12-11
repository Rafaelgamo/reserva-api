package api.reservas.api.services;

import api.reservas.api.dto.AvaliacaoDTO;
import api.reservas.api.entitys.*;
import api.reservas.api.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Transactional
    public Long criarAvaliacao(AvaliacaoDTO avaliacaoDTO) {

        var notaAvaliacao = avaliacaoDTO.notaAvaliacao();
        var avaliacao = new Avaliacao();
        avaliacao.setNotaAvaliacao(notaAvaliacao);

        var avaliar = new Avaliacao();
        avaliar.setNotaAvaliacao(avaliacaoDTO.notaAvaliacao());
        avaliar.setComentario(avaliacaoDTO.comentario());

        var avaliacaoSalva = avaliacaoRepository.save(avaliar);
        return avaliacaoSalva.getId();
    }

    @Transactional
    public Page<AvaliacaoDTO> findAll(Pageable paginacao) {
        return avaliacaoRepository.findAll(paginacao).map(AvaliacaoDTO::new);
    }

}



