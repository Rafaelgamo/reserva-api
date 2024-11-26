package api.reservas.api.infra.errors.exceptions;

public class ErroDeValidacao extends RuntimeException {
    public ErroDeValidacao(String s) {
        super(s);
    }
}