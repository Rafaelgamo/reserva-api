package api.reservas.api.usecase.dto;

import api.reservas.api.exception.ValidacaoException;

public record RestauranteDTO(
        String nome,
        String cnpj,
        EnderecoDTO endereco,
        String tipoCozinha,
        int horaAbertura,
        int horaFechamento,
        int capacidadeEmMesas
) {

    public RestauranteDTO(String nome, String cnpj, EnderecoDTO endereco, String tipoCozinha, int horaAbertura, int horaFechamento, int capacidadeEmMesas) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new ValidacaoException("Nome do restaurante necessário.");
        }

        if (endereco == null) {
            throw new ValidacaoException("Endereço do restaurante necessário.");
        }

        if (tipoCozinha == null || tipoCozinha.trim().isEmpty()) {
            throw new ValidacaoException("Tipo de cozinha necessário.");
        }

        if (capacidadeEmMesas < 0) {
            throw new ValidacaoException("A capacidadeEmMesas do restaurante não pode ser negativa.");
        }

        if (horaAbertura < 0 || horaAbertura > 24) {
            throw new ValidacaoException("Hora de abertura não pode ser negativa ou exceder 24");
        }

        if (horaFechamento < 0 || horaFechamento > 24) {
            throw new ValidacaoException("Hora de fechamento não pode ser negativa ou exceder 24");
        }

        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.tipoCozinha = tipoCozinha;
        this.horaAbertura = horaAbertura;
        this.horaFechamento = horaFechamento;
        this.capacidadeEmMesas = capacidadeEmMesas;
    }

}
