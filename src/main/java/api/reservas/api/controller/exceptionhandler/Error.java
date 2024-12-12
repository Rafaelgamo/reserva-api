package api.reservas.api.controller.exceptionhandler;

import org.springframework.http.HttpStatus;

public record Error(int errorCode, String message) {
    public Error(HttpStatus httpStatus, String message) { this(httpStatus.value(), message); }
    public Error(String message) {	this(HttpStatus.INTERNAL_SERVER_ERROR.value(), message); }
}