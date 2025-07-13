package com.softmarket.apisoftmarket.dto;

import java.math.BigDecimal;

public class DetalleVentaResponse {
  private String productoNombre;
  private int cantidad;
  private float precioUnitario;
  private float subtotal;

  public DetalleVentaResponse(String productoNombre, int cantidad, float precioUnitario, float subtotal) {
    this.productoNombre = productoNombre;
    this.cantidad = cantidad;
    this.precioUnitario = precioUnitario;
    this.subtotal = subtotal;
  }

  public DetalleVentaResponse() {
  }

  public int getCantidad() {
    return cantidad;
  }

  public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
  }

  public float getPrecioUnitario() {
    return precioUnitario;
  }

  public void setPrecioUnitario(float precioUnitario) {
    this.precioUnitario = precioUnitario;
  }

  public String getProductoNombre() {
    return productoNombre;
  }

  public void setProductoNombre(String productoNombre) {
    this.productoNombre = productoNombre;
  }

  public float getSubtotal() {
    return subtotal;
  }

  public void setSubtotal(float subtotal) {
    this.subtotal = subtotal;
  }
}
