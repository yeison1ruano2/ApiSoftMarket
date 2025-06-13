package com.softmarket.apisoftmarket.dto;

public class InventarioRequestStock {
  private String tipoMovimiento;
  private String codigoBarras;
  private Integer cantidad;

  public Integer getCantidad() {
    return cantidad;
  }

  public void setCantidad(Integer cantidad) {
    this.cantidad = cantidad;
  }

  public String getCodigoBarras() {
    return codigoBarras;
  }

  public void setCodigoBarras(String codigoBarras) {
    this.codigoBarras = codigoBarras;
  }

  public String getTipoMovimiento() {
    return tipoMovimiento;
  }

  public void setTipoMovimiento(String tipoMovimiento) {
    this.tipoMovimiento = tipoMovimiento;
  }
}
