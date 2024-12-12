package api.reservas.api.controller.mapper;

import api.reservas.api.controller.json.CadastroRestauranteJson;
import api.reservas.api.controller.json.patterns.RegexPatterns;
import api.reservas.api.usecase.dto.RestauranteDTO;

public class RestauranteDTOMapper {

    public RestauranteDTO mapToDTO(CadastroRestauranteJson cadastroRestauranteJson) {
        var horaAbertura = cadastroRestauranteJson.horaAbertura()
                .replaceAll(RegexPatterns.HORAS_MINUTOS, "$1");

        var horaFechamento = cadastroRestauranteJson.horaFechamento()
                .replaceAll(RegexPatterns.HORAS_MINUTOS, "$1");

        return new RestauranteDTO(
                cadastroRestauranteJson.nome(),
                cadastroRestauranteJson.cnpj(),
                cadastroRestauranteJson.cep(),
                cadastroRestauranteJson.numeroEndereco(),
                cadastroRestauranteJson.tipoCozinha(),
                Integer.parseInt(horaAbertura),
                Integer.parseInt(horaFechamento),
                cadastroRestauranteJson.capacidade()
        );
    }
}
