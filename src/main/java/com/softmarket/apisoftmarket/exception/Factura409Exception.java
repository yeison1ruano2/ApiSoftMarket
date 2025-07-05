package com.softmarket.apisoftmarket.exception;

import org.springframework.http.HttpStatus;

public class Factura409Exception extends RuntimeException {
  private final HttpStatus status;
  private final String message;

  public Factura409Exception(HttpStatus status,String message){
    this.status = status;
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }

  public HttpStatus getStatus() {
    return status;
  }
}
