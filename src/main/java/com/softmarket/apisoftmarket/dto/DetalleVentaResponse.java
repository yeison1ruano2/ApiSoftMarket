package com.softmarket.apisoftmarket.dto;

import java.math.BigDecimal;

public class DetalleVentaResponse {
  private String productoNombre;
  private Integer cantidad;
  private BigDecimal precioUnitario;
  private BigDecimal subtotal;

  public DetalleVentaResponse(String productoNombre, Integer cantidad, BigDecimal precioUnitario, BigDecimal subtotal) {
    this.productoNombre = productoNombre;
    this.cantidad = cantidad;
    this.precioUnitario = precioUnitario;
    this.subtotal = subtotal;
  }

  public DetalleVentaResponse() {
  }

  public Integer getCantidad() {
    return cantidad;
  }

  public void setCantidad(Integer cantidad) {
    this.cantidad = cantidad;
  }

  public BigDecimal getPrecioUnitario() {
    return precioUnitario;
  }

  public void setPrecioUnitario(BigDecimal precioUnitario) {
    this.precioUnitario = precioUnitario;
  }

  public String getProductoNombre() {
    return productoNombre;
  }

  public void setProductoNombre(String productoNombre) {
    this.productoNombre = productoNombre;
  }

  public BigDecimal getSubtotal() {
    return subtotal;
  }

  public void setSubtotal(BigDecimal subtotal) {
    this.subtotal = subtotal;
  }
}
