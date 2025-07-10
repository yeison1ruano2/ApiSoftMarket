package com.softmarket.apisoftmarket.dto;

import java.math.BigDecimal;

public class ProductoInfoWebResponse {

  private String nombreProducto;
  private String codigoBarras;
  private String marca;
  private String categoria;
  private BigDecimal ivaProducto;

  public ProductoInfoWebResponse(String marca, String categoria, String nombreProducto,String codigoBarras,BigDecimal ivaProducto) {
    this.marca = marca;
    this.categoria = categoria;
    this.nombreProducto = nombreProducto;
    this.codigoBarras = codigoBarras;
    this.ivaProducto = ivaProducto;
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

  public String getCodigoBarras() {
    return codigoBarras;
  }

  public void setCodigoBarras(String codigoBarras) {
    this.codigoBarras = codigoBarras;
  }

  public BigDecimal getIvaProducto() {
    return ivaProducto;
  }

  public void setIvaProducto(BigDecimal ivaProducto) {
    this.ivaProducto = ivaProducto;
  }
}
