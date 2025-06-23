package com.softmarket.apisoftmarket.exception;

public class RetryOperationException extends Exception {
  public RetryOperationException(String message) {
    super(message);
  }

  public RetryOperationException(String message,Throwable cause){
    super(message,cause);
  }

  public RetryOperationException(Throwable cause){
    super(cause);
  }
}
