package api.reservas.api.dto;

import api.reservas.api.entitys.Reserva;
import api.reservas.api.entitys.Restaurante;


public record RestauranteDTO(
        Long id,
        String nome,
        String endereco,
        String tipodecozinha,
        String funcionamento,
        Integer capacidade
){
        public RestauranteDTO(Restaurante restaurante) {
                this(restaurante.getId(), restaurante.getNome(), restaurante.getEndereco(),
                        restaurante.getTipodecozinha(),restaurante.getFuncionamento(), restaurante.getCapacidade());
        }

}


