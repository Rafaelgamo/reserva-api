package api.reservas.api.dto_remove;

import api.reservas.api.entitys_remove.Vaga;

public record VagaDTO(

        Long id,
        Long restaurante,
        String diaReserva,
        String horaReserva,
        Boolean vagaLivre

){

    public VagaDTO(Vaga vaga){
        this(
                vaga.getId(),
                vaga.getRestaurante(),
                vaga.getDiaReserva(),
                vaga.getHoraReserva(),
                vaga.getVagaLivre()
        );

    }

}