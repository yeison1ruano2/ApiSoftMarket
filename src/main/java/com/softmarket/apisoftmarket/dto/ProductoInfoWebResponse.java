package com.softmarket.apisoftmarket.dto;

public class ProductoInfoWebResponse {

  private String nombreProducto;
  private String marca;
  private String categoria;

  public ProductoInfoWebResponse(String marca, String categoria, String nombreProducto) {
    this.marca = marca;
    this.categoria = categoria;
    this.nombreProducto = nombreProducto;
  }

  public ProductoInfoWebResponse() {
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

  public String getNombreProducto() {
    return nombreProducto;
  }

  public void setNombreProducto(String nombreProducto) {
    this.nombreProducto = nombreProducto;
  }
}
