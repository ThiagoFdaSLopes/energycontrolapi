package br.com.energycontrol.EnergyControl.Exception;

import br.com.energycontrol.EnergyControl.Model.UserRole;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        if (ex.getCause() instanceof InvalidFormatException) {
            InvalidFormatException invalidFormat = (InvalidFormatException) ex.getCause();
            if (invalidFormat.getTargetType().equals(UserRole.class)) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("O campo user_role deve ser 'ADMIN' ou 'USER'. Valor fornecido: " + invalidFormat.getValue());
            }
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Erro ao processar a requisição: " + ex.getMessage());
    }
}