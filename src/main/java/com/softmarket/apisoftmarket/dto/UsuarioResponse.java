package com.softmarket.apisoftmarket.dto;

public class UsuarioResponse {
  private String nombre;
  private String apellido;
  private String identificacion;

  public UsuarioResponse(String nombre, String apellido, String identificacion) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.identificacion = identificacion;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getIdentificacion() {
    return identificacion;
  }

  public void setIdentificacion(String identificacion) {
    this.identificacion = identificacion;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
}
