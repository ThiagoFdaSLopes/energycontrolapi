package br.com.energycontrol.EnergyControl.advice;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException error) {

        Map<String, String> map = new HashMap<>();
        List<FieldError> fieldErrors = error.getBindingResult().getFieldErrors();

        for (FieldError fieldError : fieldErrors) {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return map;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, String> handleIntegrityViolation(DataIntegrityViolationException ex) {
        Map<String, String> errorMap = new HashMap<>();

        Throwable cause = ex.getRootCause();
        String errorMessage = "Violação de integridade de dados detectada.";

        if (cause instanceof SQLException sqlException) {
            int errorCode = sqlException.getErrorCode();
            String sqlMessage = sqlException.getMessage();

            switch (errorCode) {
                case 1:
                    errorMessage = "Registro duplicado. Um log com esse identificador já existe.";
                    break;
                case 1400:
                    errorMessage = "Um campo obrigatório não foi preenchido.";
                    break;
                case 2291:
                    errorMessage = "Referência inválida. O registro relacionado não existe.";
                    break;
                default:
                    errorMessage = "Erro de integridade: " + sqlMessage;
                    break;
            }
        } else if (cause != null) {
            errorMessage = "Erro de integridade: " + cause.getMessage();
        }

        errorMap.put("erro", errorMessage);
        return errorMap;
    }
}
