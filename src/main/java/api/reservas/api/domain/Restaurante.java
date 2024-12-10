package api.reservas.api.domain;

public record Restaurante (
        String nome,
        String cnpj,
        Endereco endereco,
        String tipoCozinha,
        int horaAbertura,
        int horaFechamento,
        int capacidade
) {

    public Restaurante {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do restaurante necessário.");
        }

        var trimmedCnpj = cnpj.trim();
        var cnpjDigits = trimmedCnpj.replaceAll("\\D", "");
        if (trimmedCnpj.isEmpty() || cnpjDigits.length() != 14) {
            throw new IllegalArgumentException("CNPJ é necessário.");
        }

        if (endereco == null) {
            throw new IllegalArgumentException("Endereço do restaurante necessário.");
        }

        if (tipoCozinha == null || tipoCozinha.trim().isEmpty()) {
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
    }

    public boolean horaReservaValida(int horaParaReserva) {
        var horaReservaValida = horaParaReserva >= horaAbertura
                                    && horaParaReserva < horaFechamento;
        return horaReservaValida;
    }
}
