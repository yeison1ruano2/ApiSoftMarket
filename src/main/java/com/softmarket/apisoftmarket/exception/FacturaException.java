package com.softmarket.apisoftmarket.exception;

import org.springframework.http.HttpStatus;

public class FacturaException extends RuntimeException{

  private final HttpStatus status;
  private final String body;

  public FacturaException(String body, HttpStatus status) {
    super("Error Factura: " + status);
    this.body = body;
    this.status = status;
  }

  public String getBody() {
    return body;
  }

  public HttpStatus getStatus() {
    return status;
  }
}
