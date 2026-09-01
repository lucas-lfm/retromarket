package br.edu.ifce.retromarket.controllers.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ControllerExecptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException ex, HttpServletRequest req) {

    CustomError error = new CustomError();
    error.setTimestamp(Instant.now());
    error.setStatus(404);
    error.setError(ex.getMessage());
    error.setPath(req.getRequestURI());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ValidationError> validationError(MethodArgumentNotValidException ex, HttpServletRequest req) {

    ValidationError error = new ValidationError();
    error.setTimestamp(Instant.now());
    error.setStatus(HttpStatus.UNPROCESSABLE_CONTENT.value()); // 422
    error.setError("Dados inválidos.");
    error.setPath(req.getRequestURI());

    for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
      error.addError(fieldError.getField(), fieldError.getDefaultMessage());
    }

    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(error);

  }

}
