package api.reservas.api.controller.exceptionhandler;

import api.reservas.api.exception.SystemBaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ReservaExceptionHandler {

    @ExceptionHandler(SystemBaseException.class)
    public ResponseEntity<Error> handleSystemBaseException(SystemBaseException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(new Error(ex.getHttpStatus(), ex.getLocalizedMessage()));
    }

}
