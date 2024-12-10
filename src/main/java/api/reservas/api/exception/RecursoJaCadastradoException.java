package api.reservas.api.exception;

import org.springframework.http.HttpStatus;

public class RecursoJaCadastradoException extends SystemBaseException {

    private final String message;

    public <T> RecursoJaCadastradoException(Class<T> entityClass, String fieldName, Object value) {
        this.message = entityClass.getSimpleName() + " com '" + fieldName + "' igual a '" +value.toString() + "' ja cadastrado.";
    }

    @Override
    public Integer getHttpStatus() {
        return HttpStatus.CONFLICT.value();
    }

    @Override
    public String getMessage() {
        return message;
    }

}
