package api.reservas.api.exception;

import org.springframework.http.HttpStatus;

public class RecursoNaoEncontradoException extends SystemBaseException {

    private final String message;

    public <T> RecursoNaoEncontradoException(Class<T> entityClass, String fieldName, Object value) {
        this.message = entityClass.getSimpleName() + " com '" + fieldName + "' igual a '" +value.toString() + "' não encontrado.";
    }

    @Override
    public Integer getHttpStatus() {
        return HttpStatus.NOT_FOUND.value();
    }

    @Override
    public String getMessage() {
        return message;
    }

}
