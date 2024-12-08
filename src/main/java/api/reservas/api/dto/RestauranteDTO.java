package api.reservas.api.dto;


import api.reservas.api.entitys.Restaurante;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@EnableSpringDataWebSupport
public record RestauranteDTO(
        Long id,
        String nome,
        String endereco,
        String tipodecozinha,
        String funcionamento,
        Integer capacidade,
        Boolean ativo
){
        public RestauranteDTO(Restaurante restaurante) {
                this(
                        restaurante.getId(),
                        restaurante.getNome(),
                        restaurante.getEndereco(),
                        restaurante.getTipodecozinha(),
                        restaurante.getFuncionamento(),
                        restaurante.getCapacidade(),
                        restaurante.getAtivo()
                );
        }

}