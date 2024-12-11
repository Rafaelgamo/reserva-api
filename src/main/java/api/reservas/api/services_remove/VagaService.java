package api.reservas.api.services_remove;

import api.reservas.api.dto_remove.VagaDTO;
import api.reservas.api.entitys_remove.Restaurante;
import api.reservas.api.entitys_remove.Vaga;
import api.reservas.api.repository_remove.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VagaService {

    @Autowired
    private VagaRepository vagaRepository;

    //registrar horarios para reserva
    @Transactional
    public Long criarVaga(VagaDTO vagaDTO) {

        var idRestaurante = vagaDTO.restaurante();

        var restaurante = new Restaurante();
        restaurante.setId(idRestaurante);

        var vaga = new Vaga();
        vaga.setRestaurante(restaurante);
        vaga.setDiaReserva(vagaDTO.diaReserva());
        vaga.setHoraReserva(vagaDTO.horaReserva());
        vaga.setVagaLivre(true);

        var vagaSalva = vagaRepository.save(vaga);
        return vagaSalva.getId();
    }

    //listar horarios
    @Transactional
    public Vaga buscaPorId(Long id) {
       Vaga vaga = vagaRepository.getReferenceById(id);
       return vaga;
    }

    //cancelar vaga de reserva
    @Transactional
    public void removerVaga(Long id) {
        Optional<Vaga> vaga = vagaRepository.findById(id);
        vaga.ifPresent(value -> value.setVagaLivre(false));
    }

}












