package api.reservas.api.domain;

import api.reservas.api.exception.ValidacaoException;

public record Restaurante (
        String nome,
        String cnpj,
        Endereco endereco,
        String tipoCozinha,
        int horaAbertura,
        int horaFechamento,
        int capacidadeEmMesas
) {

    public Restaurante {
        if (nome == null || nome.trim().isEmpty()) {
            throw new ValidacaoException("Nome do restaurante necessário.");
        }

        var trimmedCnpj = cnpj.trim();
        var cnpjDigits = trimmedCnpj.replaceAll("\\D", "");
        if (trimmedCnpj.isEmpty() || cnpjDigits.length() != 14) {
            throw new ValidacaoException("CNPJ é necessário.");
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
    }

    public boolean horaReservaValida(int horaParaReserva) {
        var horaReservaValida = horaParaReserva >= horaAbertura
                                    && horaParaReserva < horaFechamento;
        return horaReservaValida;
    }
}
