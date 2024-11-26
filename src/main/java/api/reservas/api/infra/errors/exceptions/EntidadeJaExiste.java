package api.reservas.api.infra.errors.exceptions;

public class EntidadeJaExiste extends RuntimeException {
    public <T> EntidadeJaExiste(Class<T> entidade, String campo, Object valor) {
        super(" ja possui cadastrado com esse " + campo + "(" + valor + ")");
    }
}



