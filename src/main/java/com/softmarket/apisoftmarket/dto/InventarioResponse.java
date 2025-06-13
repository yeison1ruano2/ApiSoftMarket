package com.softmarket.apisoftmarket.dto;

public class InventarioResponse {
  private String nombreProducto;
  private String codigoBarras;
  private Integer stock;
  private String status;
  private String mensaje;

  public InventarioResponse(String status, String mensaje) {
    this.mensaje = mensaje;
    this.status = status;
  }

  public InventarioResponse(String nombreProducto,String codigoBarras,Integer stock,String status,String mensaje){
    this.nombreProducto = nombreProducto;
    this.codigoBarras = codigoBarras;
    this.stock = stock;
    this.status = status;
    this.mensaje = mensaje;
  }

  public InventarioResponse() {
  }

  public String getMensaje() {
    return mensaje;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getCodigoBarras() {
    return codigoBarras;
  }

  public void setCodigoBarras(String codigoBarras) {
    this.codigoBarras = codigoBarras;
  }

  public String getNombreProducto() {
    return nombreProducto;
  }

  public void setNombreProducto(String nombreProducto) {
    this.nombreProducto = nombreProducto;
  }

  public Integer getStock() {
    return stock;
  }

  public void setStock(Integer stock) {
    this.stock = stock;
  }
}
