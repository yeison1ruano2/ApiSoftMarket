package com.softmarket.apisoftmarket.dto;

import java.math.BigDecimal;

public class ProductoRequest {
  private String nombre;
  private String codigoBarras;
  private BigDecimal precioVenta;
  private BigDecimal precioPorMayor;
  private BigDecimal precioCredito;
  private Integer cantidadMinimaMayor;
  private String marca;
  private Integer stockMinimo;
  private String categoria;

  public String getCodigoBarras() {
    return codigoBarras;
  }


  public void setCodigoBarras(String codigoBarras) {
    this.codigoBarras = codigoBarras;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Integer getStockMinimo() {
    return stockMinimo;
  }

  public void setStockMinimo(Integer stockMinimo) {
    this.stockMinimo = stockMinimo;
  }

  public String getCategoria() {
    return categoria;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  public String getMarca() {
    return marca;
  }

  public void setMarca(String marca) {
    this.marca = marca;
  }

  public Integer getCantidadMinimaMayor() {
    return cantidadMinimaMayor;
  }

  public void setCantidadMinimaMayor(Integer cantidadMinimaMayor) {
    this.cantidadMinimaMayor = cantidadMinimaMayor;
  }

  public BigDecimal getPrecioPorMayor() {
    return precioPorMayor;
  }

  public void setPrecioPorMayor(BigDecimal precioPorMayor) {
    this.precioPorMayor = precioPorMayor;
  }

  public BigDecimal getPrecioVenta() {
    return precioVenta;
  }

  public void setPrecioVenta(BigDecimal precioVenta) {
    this.precioVenta = precioVenta;
  }

  public BigDecimal getPrecioCredito() {
    return precioCredito;
  }

  public void setPrecioCredito(BigDecimal precioCredito) {
    this.precioCredito = precioCredito;
  }
}
