package com.softmarket.apisoftmarket.dto;

public class DetalleVentaRequest {
  private String productoId;
  private int cantidad;

  public int getCantidad() {
    return cantidad;
  }

  public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
  }

  public String getProductoId() {
    return productoId;
  }

  public void setProductoId(String productoId) {
    this.productoId = productoId;
  }
}
