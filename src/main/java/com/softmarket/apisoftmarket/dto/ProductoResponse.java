package com.softmarket.apisoftmarket.dto;

import java.math.BigDecimal;

public class ProductoResponse {

  private String nombre;
  private String codigoBarras;
  private MarcaResponse marca;
  private BigDecimal precioVenta;
  private BigDecimal precioPorMayor;
  private Integer cantidadMinimaMayor;
  private CategoriaResponse categoria;
  private String mensaje;
  private String status;

  public ProductoResponse(String nombre, String codigoBarras, MarcaResponse marca, BigDecimal precioVenta, CategoriaResponse categoria, String mensaje,String status) {
    this.nombre = nombre;
    this.codigoBarras = codigoBarras;
    this.marca = marca;
    this.precioVenta = precioVenta;
    this.categoria = categoria;
    this.mensaje = mensaje;
    this.status = status;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public ProductoResponse() {
  }

  public String getCodigoBarras() {
    return codigoBarras;
  }

  public ProductoResponse(String status,String mensaje) {
    this.mensaje = mensaje;
    this.status=status;
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

  public String getMensaje() {
    return mensaje;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }

  public Integer getCantidadMinimaMayor() {
    return cantidadMinimaMayor;
  }

  public void setCantidadMinimaMayor(Integer cantidadMinimaMayor) {
    this.cantidadMinimaMayor = cantidadMinimaMayor;
  }

  public CategoriaResponse getCategoria() {
    return categoria;
  }

  public void setCategoria(CategoriaResponse categoria) {
    this.categoria = categoria;
  }

  public MarcaResponse getMarca() {
    return marca;
  }

  public void setMarca(MarcaResponse marca) {
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
