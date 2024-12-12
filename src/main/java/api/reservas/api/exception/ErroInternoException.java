package api.reservas.api.exception;

import org.springframework.http.HttpStatus;

public class ErroInternoException extends SystemBaseException {

    private final String message;

    public ErroInternoException(String message) {
        this.message = String.format("Erro interno: %s", message);
    }

    @Override
    public Integer getHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    @Override
    public String getMessage() {
        return message;
    }

}
