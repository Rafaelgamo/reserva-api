package api.reservas.api.domain;

import java.util.Objects;

public record Restaurante (
        String nome,
        Endereco endereco,
        String tipoCozinha,
        int horaAbertura,
        int horaFechamento,
        int capacidade
) {

    public Restaurante(String nome, Endereco endereco, String tipoCozinha, int horaAbertura, int horaFechamento, int capacidade) {
        if (Objects.isNull(nome) || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do restaurante necessário.");
        }

        if (Objects.isNull(endereco)) {
            throw new IllegalArgumentException("Endereço do restaurante necessário.");
        }

        if (Objects.isNull(tipoCozinha) || tipoCozinha.trim().isEmpty()) {
            throw new IllegalArgumentException("Tipo de cozinha necessário.");
        }

        if (capacidade < 0) {
            throw new IllegalArgumentException("A capacidade do restaurante não pode ser negativa.");
        }

        if (horaAbertura < 0 || horaAbertura > 24) {
            throw new IllegalArgumentException("Hora de abertura não pode ser negativa ou exceder 24");
        }

        if (horaFechamento < 0 || horaFechamento > 24) {
            throw new IllegalArgumentException("Hora de fechamento não pode ser negativa ou exceder 24");
        }

        this.nome = nome;
        this.endereco = endereco;
        this.tipoCozinha = tipoCozinha;
        this.horaAbertura = horaAbertura;
        this.horaFechamento = horaFechamento;
        this.capacidade = capacidade;
    }

    public boolean horaReservaValida(int horaParaReserva) {
        var horaReservaValida = horaParaReserva >= horaAbertura && horaParaReserva < horaFechamento;
        return horaReservaValida;
    }
}
