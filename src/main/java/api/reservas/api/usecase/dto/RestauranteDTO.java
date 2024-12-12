package api.reservas.api.usecase.dto;

public record RestauranteDTO(
        String nome,
        String cnpj,
        String cep,
        String numeroEndereco,
        String tipoCozinha,
        int horaAbertura,
        int horaFechamento,
        int capacidadeEmMesas
) {

    public RestauranteDTO(String nome, String cnpj, String cep, String numeroEndereco, String tipoCozinha, int horaAbertura, int horaFechamento, int capacidadeEmMesas) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do restaurante necessário.");
        }

        if (cep == null) {
            throw new IllegalArgumentException("Endereço do restaurante necessário.");
        }

        if (tipoCozinha == null || tipoCozinha.trim().isEmpty()) {
            throw new IllegalArgumentException("Tipo de cozinha necessário.");
        }

        if (capacidadeEmMesas < 0) {
            throw new IllegalArgumentException("A capacidadeEmMesas do restaurante não pode ser negativa.");
        }

        if (horaAbertura < 0 || horaAbertura > 24) {
            throw new IllegalArgumentException("Hora de abertura não pode ser negativa ou exceder 24");
        }

        if (horaFechamento < 0 || horaFechamento > 24) {
            throw new IllegalArgumentException("Hora de fechamento não pode ser negativa ou exceder 24");
        }

        this.nome = nome;
        this.cnpj = cnpj;
        this.cep = cep;
        this.numeroEndereco = numeroEndereco;
        this.tipoCozinha = tipoCozinha;
        this.horaAbertura = horaAbertura;
        this.horaFechamento = horaFechamento;
        this.capacidadeEmMesas = capacidadeEmMesas;
    }

}
