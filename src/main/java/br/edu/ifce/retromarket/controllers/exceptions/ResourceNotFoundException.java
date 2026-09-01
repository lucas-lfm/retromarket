package br.edu.ifce.retromarket.controllers.exceptions;

public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException(String msg) {
    super(msg);
  }

}
