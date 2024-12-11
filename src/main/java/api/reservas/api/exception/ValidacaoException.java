package api.reservas.api.exception;

import org.springframework.http.HttpStatus;

public class ValidacaoException extends SystemBaseException {

    private final String message;

    public ValidacaoException(String message) {
        this.message = message;
    }

    @Override
    public Integer getHttpStatus() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return message;
    }

}
