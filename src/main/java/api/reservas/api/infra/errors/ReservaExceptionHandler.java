package api.reservas.api.infra.errors;

import api.reservas.api.infra.errors.exceptions.EntidadeJaExiste;
import api.reservas.api.infra.errors.exceptions.ErroDeValidacao;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class ReservaExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> tratarError500(EntidadeJaExiste ex) {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new Erro(ex.getMessage()));
    }

    @ExceptionHandler(ErroDeValidacao.class)
    public ResponseEntity<Object> tratarErroDeValidacao(ErroDeValidacao ex) {
        return ResponseEntity.badRequest().body(new Erro(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao :: new).toList());
    }

    private record DadosErroValidacao(String campo, String mensagem){
        public DadosErroValidacao(FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

    private record Erro(String erro) {}
}
