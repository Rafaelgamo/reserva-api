package api.reservas.api.controller.json;

import api.reservas.api.controller.json.patterns.RegexPatterns;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CadastroRestauranteJson(
        @NotBlank String nome,
        @NotBlank String cnpj,
        @NotBlank String cep,
        @NotBlank String numeroEndereco,
        @NotBlank String tipoCozinha,
        @NotBlank @Pattern(regexp = RegexPatterns.HORAS_MINUTOS) String horaAbertura,
        @NotBlank @Pattern(regexp = RegexPatterns.HORAS_MINUTOS) String horaFechamento,
        @NotNull Integer capacidade
) { }
