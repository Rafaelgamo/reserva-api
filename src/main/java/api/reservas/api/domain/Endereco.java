package api.reservas.api.domain;

public record Endereco(
        String cep,
        String numero
) {
    public Endereco(String cep, String numero) {
        var formatoCepValido = formatoCepValido(cep);

        if (cep.trim().isEmpty() || !formatoCepValido) {
            throw new IllegalArgumentException("CEP é necessário e precisa ter 8 dígitos");
        }

        if (numero.trim().isEmpty()) {
            throw new IllegalArgumentException("Número do endereço é necessário");
        }

        this.cep = cep;
        this.numero = numero;
    }

    private boolean formatoCepValido(String cep) {
        var cepTrim = cep.trim().replaceAll("\\D", "");
        return cepTrim.length() == 8;
    }
}
