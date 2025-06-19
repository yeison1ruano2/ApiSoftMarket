package com.softmarket.apisoftmarket.dto;

public class UserResponse {
  private Integer status;
  private String token;
  private String nombre;
  private String mensaje;

  public UserResponse(Integer status,String token,String nombre,String mensaje) {
    this.status = status;
    this.token = token;
    this.nombre= nombre;
    this.mensaje = mensaje;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getMensaje() {
    return mensaje;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
}
