package com.softmarket.apisoftmarket.dto;

import java.math.BigDecimal;

public class ClienteResponse {
  private String id;
  private String nombre;
  private String apellido;
  private String identificacion;
  private String telefono;
  private String direccion;
  private BigDecimal creditoMaximo;
  private BigDecimal cupoTotal;
  private String status;
  private String mensaje;

  public ClienteResponse(String id, String nombre, String apellido, String identificacion, String telefono, String direccion, BigDecimal creditoMaximo, BigDecimal cupoTotal, String status, String mensaje) {
    this.id = id;
    this.nombre = nombre;
    this.apellido = apellido;
    this.identificacion = identificacion;
    this.telefono = telefono;
    this.direccion = direccion;
    this.creditoMaximo = creditoMaximo;
    this.cupoTotal = cupoTotal;
    this.status = status;
    this.mensaje = mensaje;
  }

  public ClienteResponse(String nombre, String apellido, String identificacion, String telefono, String direccion) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.identificacion = identificacion;
    this.telefono = telefono;
    this.direccion = direccion;
  }

  public ClienteResponse(String status, String mensaje) {
    this.status = status;
    this.mensaje = mensaje;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public BigDecimal getCreditoMaximo() {
    return creditoMaximo;
  }

  public void setCreditoMaximo(BigDecimal creditoMaximo) {
    this.creditoMaximo = creditoMaximo;
  }

  public BigDecimal getCupoTotal() {
    return cupoTotal;
  }

  public void setCupoTotal(BigDecimal cupoTotal) {
    this.cupoTotal = cupoTotal;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public String getIdentificacion() {
    return identificacion;
  }

  public void setIdentificacion(String identificacion) {
    this.identificacion = identificacion;
  }

  public String getMensaje() {
    return mensaje;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }
}
