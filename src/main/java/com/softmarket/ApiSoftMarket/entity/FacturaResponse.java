package com.softmarket.ApiSoftMarket.entity;

public class FacturaResponse {
  private String status;
  private String message;
  private DataResponse data;

  public DataResponse getData() {
    return data;
  }

  public void setData(DataResponse data) {
    this.data = data;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
