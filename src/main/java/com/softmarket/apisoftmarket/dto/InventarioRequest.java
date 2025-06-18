package com.softmarket.apisoftmarket.dto;

public class InventarioRequest {
  private Integer stockMinimo;
  private Integer cantidad;
  private String observacion;

  public InventarioRequest(Integer stockMinimo,Integer cantidad, String observacion) {
    this.stockMinimo = stockMinimo;
    this.cantidad = cantidad;
    this.observacion = observacion;
  }

  public InventarioRequest() {
  }

  public Integer getStockMinimo() {
    return stockMinimo;
  }

  public void setStockMinimo(Integer stockMinimo) {
    this.stockMinimo = stockMinimo;
  }

  public Integer getCantidad() {
    return cantidad;
  }

  public void setCantidad(Integer cantidad) {
    this.cantidad = cantidad;
  }

  public String getObservacion() {
    return observacion;
  }

  public void setObservacion(String observacion) {
    this.observacion = observacion;
  }
}
