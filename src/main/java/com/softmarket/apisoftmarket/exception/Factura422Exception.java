package com.softmarket.apisoftmarket.exception;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

public class Factura422Exception extends RuntimeException{
  private final HttpStatus status;
  private final String message;
  private final Map<String, List<String>> errors;
  public Factura422Exception(HttpStatus status, String message, Map<String, List<String>> errors) {
    super("Error Factura: " + status);
    this.status = status;
    this.message = message;
    this.errors = errors;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }

  public Map<String, List<String>> getErrors() {
    return errors;
  }
}
