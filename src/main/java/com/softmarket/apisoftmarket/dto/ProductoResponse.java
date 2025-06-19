package com.softmarket.apisoftmarket.dto;

import java.math.BigDecimal;

public class ProductoResponse {

  private String nombre;
  private String codigoBarras;
  private String marca;
  private BigDecimal precioVenta;
  private BigDecimal precioPorMayor;
  private Integer cantidadMinimaMayor;
  private String categoria;

  public ProductoResponse(String nombre, String codigoBarras, String marca, BigDecimal precioVenta, String categoria) {
    this.nombre = nombre;
    this.codigoBarras = codigoBarras;
    this.marca = marca;
    this.precioVenta = precioVenta;
    this.categoria = categoria;
  }

  public ProductoResponse() {
  }

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

  public Integer getCantidadMinimaMayor() {
    return cantidadMinimaMayor;
  }

  public void setCantidadMinimaMayor(Integer cantidadMinimaMayor) {
    this.cantidadMinimaMayor = cantidadMinimaMayor;
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
}
