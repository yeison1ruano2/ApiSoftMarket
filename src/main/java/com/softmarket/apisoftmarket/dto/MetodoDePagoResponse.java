package com.softmarket.apisoftmarket.dto;

public class MetodoDePagoResponse {

  private String codigo;
  private String nombre;

  public MetodoDePagoResponse(String codigo, String nombre) {
    this.codigo = codigo;
    this.nombre = nombre;
  }

  public MetodoDePagoResponse() {
  }

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
}

