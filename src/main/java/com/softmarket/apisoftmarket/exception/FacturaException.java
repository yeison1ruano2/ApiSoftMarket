package com.softmarket.apisoftmarket.exception;

import org.springframework.http.HttpStatus;

public class FacturaException extends RuntimeException{
  private final HttpStatus status;
  private final String body;

  public FacturaException(HttpStatus status, String body) {
    super("Error Factura: " + status);
    this.status = status;
    this.body = body;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public String getBody() {
    return body;
  }
}
