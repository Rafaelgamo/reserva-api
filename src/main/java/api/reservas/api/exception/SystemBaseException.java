package api.reservas.api.exception;

public abstract class SystemBaseException extends RuntimeException {

    public abstract Integer getHttpStatus();
    @Override
    public abstract String getMessage();

}