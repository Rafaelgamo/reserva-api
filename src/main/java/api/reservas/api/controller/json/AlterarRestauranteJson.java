package api.reservas.api.controller.json;

public record AlterarRestauranteJson(
        String nome,
        EnderecoJson endereco,
        String tipoCozinha,
        Integer horaAbertura,
        Integer horaFechamento,
        Integer capacidadeEmMesas
) {
}
