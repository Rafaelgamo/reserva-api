package api.reservas.api.gateway;

public interface TipoCozinhaGateway {
    Long buscarOuCriarTipoCozinha(String tipoCozinha);
    void excluir(String tipoCozinha);
}
