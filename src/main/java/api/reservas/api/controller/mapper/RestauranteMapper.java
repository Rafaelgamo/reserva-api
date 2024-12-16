package api.reservas.api.controller.mapper;

import api.reservas.api.controller.json.CadastroRestauranteJson;
import api.reservas.api.controller.json.patterns.RegexPatterns;
import api.reservas.api.domain.restaurante.Restaurante;
import api.reservas.api.usecase.dto.EnderecoDTO;
import api.reservas.api.usecase.dto.RestauranteDTO;

public class RestauranteMapper {

    public RestauranteDTO mapToDTO(CadastroRestauranteJson cadastroRestauranteJson) {
        var horaAbertura = cadastroRestauranteJson.horaAbertura()
                .replaceAll(RegexPatterns.HORAS_MINUTOS, "$1");

        var horaFechamento = cadastroRestauranteJson.horaFechamento()
                .replaceAll(RegexPatterns.HORAS_MINUTOS, "$1");

        var enderecoDTO = new EnderecoDTO(cadastroRestauranteJson.cep(), cadastroRestauranteJson.numeroEndereco());
        return new RestauranteDTO(
                cadastroRestauranteJson.nome(),
                cadastroRestauranteJson.cnpj(),
                enderecoDTO,
                cadastroRestauranteJson.tipoCozinha(),
                Integer.parseInt(horaAbertura),
                Integer.parseInt(horaFechamento),
                cadastroRestauranteJson.capacidadeEmMesas()
        );
    }

    public RestauranteDTO mapToDTO(Restaurante restaurante) {
        var endereco = restaurante.endereco();
        var enderecoDTO = new EnderecoDTO(endereco.cep(), endereco.numero());
        return new RestauranteDTO(
                restaurante.nome(),
                restaurante.cnpj(),
                enderecoDTO,
                restaurante.tipoCozinha(),
                restaurante.horaAbertura(),
                restaurante.horaFechamento(),
                restaurante.capacidadeEmMesas()
        );
    }
}
