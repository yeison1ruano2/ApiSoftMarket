package com.softmarket.apisoftmarket.dto;

public class DetalleVentaRequest {
  private String productoId;
  private Integer cantidad;

  public Integer getCantidad() {
    return cantidad;
  }

  public void setCantidad(Integer cantidad) {
    this.cantidad = cantidad;
  }

  public String getProductoId() {
    return productoId;
  }

  public void setProductoId(String productoId) {
    this.productoId = productoId;
  }
}
