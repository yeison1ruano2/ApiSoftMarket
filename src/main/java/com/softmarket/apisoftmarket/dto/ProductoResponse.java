package com.softmarket.apisoftmarket.dto;

public class ProductoResponse {

  private String nombre;
  private String codigoBarras;
  private String marca;
  private float precioVenta;
  private float precioPorMayor;
  private int cantidadMinimaMayor;
  private String categoria;
  private int stock;
  private int iva;

  public ProductoResponse(String nombre, String codigoBarras, String marca, float precioVenta, String categoria) {
    this.nombre = nombre;
    this.codigoBarras = codigoBarras;
    this.marca = marca;
    this.precioVenta = precioVenta;
    this.categoria = categoria;
  }

  public ProductoResponse(String nombre, String codigoBarras,float precioVenta, float precioPorMayor,int cantidadMinimaMayor, String marca,String categoria, int stock,int iva) {
    this.nombre = nombre;
    this.codigoBarras = codigoBarras;
    this.precioVenta = precioVenta;
    this.precioPorMayor = precioPorMayor;
    this.cantidadMinimaMayor = cantidadMinimaMayor;
    this.marca = marca;
    this.categoria = categoria;
    this.stock = stock;
    this.iva = iva;
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

  public Integer getStock() {
    return stock;
  }

  public void setStock(Integer stock) {
    this.stock = stock;
  }

  public int getIva() {
    return iva;
  }

  public void setIva(int iva) {
    this.iva = iva;
  }
}
