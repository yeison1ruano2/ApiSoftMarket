package com.softmarket.apisoftmarket.exception;

public class RetryInterruptedException extends RetryOperationException {
  public RetryInterruptedException(String operationName,Throwable cause) {

    super("Proceso interrumpido durante retry de " + operationName, cause);
  }
}
