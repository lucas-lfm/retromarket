package br.edu.ifce.retromarket.controllers.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<CustomError> resourceNotFound(
      ResourceNotFoundException ex,
      HttpServletRequest req) {
    CustomError error = new CustomError();
    error.setTimestamp(Instant.now());
    error.setStatus(404);
    error.setError(ex.getMessage());
    error.setPath(req.getRequestURI());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }

}
