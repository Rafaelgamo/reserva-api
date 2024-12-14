package api.reservas.api.usecase.dto;

import api.reservas.api.controller.json.EnderecoJson;
import api.reservas.api.exception.ValidacaoException;
import api.reservas.api.util.ValidadorFormatoUtil;

public record EnderecoDTO(
        String cep,
        String numero
) {
    public EnderecoDTO {
        if (cep == null || cep.isBlank() || numero == null || numero.isBlank() || !ValidadorFormatoUtil.formatoCepValido(cep)) {
            throw new ValidacaoException("Erro ao criar endereco com: cep=" + cep + ", numero=" + numero);
        }
    }

    public EnderecoDTO(EnderecoJson endereco) {
        this(endereco.cep(), endereco.numero());
    }
}
