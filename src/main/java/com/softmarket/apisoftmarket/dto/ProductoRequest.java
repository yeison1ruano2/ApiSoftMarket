package com.softmarket.apisoftmarket.dto;

public class ProductoRequest {
  private String nombre;
  private String codigoBarras;
  private float precioVenta;
  private float precioPorMayor;
  private float precioCredito;
  private int cantidadMinimaMayor;
  private String marca;
  private int stockMinimo;
  private String categoria;
  private int iva;

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

  public float getPrecioPorMayor() {
    return precioPorMayor;
  }

  public void setPrecioPorMayor(float precioPorMayor) {
    this.precioPorMayor = precioPorMayor;
  }

  public float getPrecioVenta() {
    return precioVenta;
  }

  public void setPrecioVenta(float precioVenta) {
    this.precioVenta = precioVenta;
  }

  public float getPrecioCredito() {
    return precioCredito;
  }

  public void setPrecioCredito(float precioCredito) {
    this.precioCredito = precioCredito;
  }

  public int getIva() {
    return iva;
  }

  public void setIva(int iva) {
    this.iva = iva;
  }
}
