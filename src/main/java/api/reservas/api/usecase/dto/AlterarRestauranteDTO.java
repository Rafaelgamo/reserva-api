package api.reservas.api.usecase.dto;

import api.reservas.api.controller.json.AlterarRestauranteJson;

public record AlterarRestauranteDTO (
        String nome,
        EnderecoDTO endereco,
        String tipoCozinha,
        Integer horaAbertura,
        Integer horaFechamento,
        Integer capacidadeEmMesas
) {
    public AlterarRestauranteDTO(AlterarRestauranteJson alterarRestauranteJson) {
        this(
                alterarRestauranteJson.nome(),
                new EnderecoDTO(alterarRestauranteJson.endereco()),
                alterarRestauranteJson.tipoCozinha(),
                alterarRestauranteJson.horaAbertura(),
                alterarRestauranteJson.horaFechamento(),
                alterarRestauranteJson.capacidadeEmMesas()
        );
    }
}
