package com.softmarket.apisoftmarket.dto;

public class GenericResponse {
  private String status;
  private String mensaje;

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

  public GenericResponse(String status,String mensaje) {
    this.status = status;
    this.mensaje = mensaje;
  }
}
