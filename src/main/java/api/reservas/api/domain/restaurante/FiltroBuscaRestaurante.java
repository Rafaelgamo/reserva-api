package api.reservas.api.domain.restaurante;

public record FiltroBuscaRestaurante(
        String nome,
        String cep,
        String tipoCozinha
) {
}
