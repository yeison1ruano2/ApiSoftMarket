package com.softmarket.apisoftmarket.dto;

public class StockResponse {
  private Integer stock;

  public StockResponse(Integer stock) {
    this.stock = stock;
  }

  public StockResponse() {
  }

  public Integer getStock() {
    return stock;
  }

  public void setStock(Integer stock) {
    this.stock = stock;
  }
}
