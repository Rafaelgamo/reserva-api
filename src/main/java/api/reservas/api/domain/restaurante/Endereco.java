package api.reservas.api.domain.restaurante;

import api.reservas.api.exception.ValidacaoException;
import api.reservas.api.util.ValidadorFormatoUtil;

public record Endereco(
        String cep,
        String numero
) {
    public Endereco(String cep, String numero) {
        var formatoCepValido = ValidadorFormatoUtil.formatoCepValido(cep);

        if (cep.trim().isEmpty() || !formatoCepValido) {
            throw new ValidacaoException("CEP é necessário e precisa ter 8 dígitos");
        }

        this.cep = cep;
        this.numero = numero;
    }
}
