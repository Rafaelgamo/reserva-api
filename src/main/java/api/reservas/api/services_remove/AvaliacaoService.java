package api.reservas.api.services_remove;

import api.reservas.api.dto_remove.AvaliacaoDTO;
import api.reservas.api.entitys_remove.Avaliacao;
import api.reservas.api.repository_remove.AvaliacaoRepository;
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



