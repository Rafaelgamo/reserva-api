package api.reservas.api.dto;


import api.reservas.api.entitys.Vaga;

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
                vaga.getRestaurante().getId(),
                vaga.getDiaReserva(),
                vaga.getHoraReserva(),
                vaga.getVagaLivre()
        );

    }

}