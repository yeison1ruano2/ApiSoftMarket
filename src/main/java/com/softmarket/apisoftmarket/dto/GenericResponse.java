package com.softmarket.apisoftmarket.dto;

public class GenericResponse {
  private Integer status;
  private String mensaje;

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

  public GenericResponse(Integer status,String mensaje) {
    this.status = status;
    this.mensaje = mensaje;
  }
}
