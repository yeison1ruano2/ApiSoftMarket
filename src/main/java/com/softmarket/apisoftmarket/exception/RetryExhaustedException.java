package com.softmarket.apisoftmarket.exception;

public class RetryExhaustedException extends RetryOperationException {
  private final int maxRetries;

  public RetryExhaustedException(String operationName, int maxRetries, Throwable cause) {
    super("Falló " + operationName + " después de " + maxRetries + " intentos", cause);
    this.maxRetries = maxRetries;
  }

  public int getMaxRetries() {
    return maxRetries;
  }
}
