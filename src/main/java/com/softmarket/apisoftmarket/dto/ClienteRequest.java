package com.softmarket.apisoftmarket.dto;

import java.math.BigDecimal;

public class ClienteRequest {

  private String nombre;
  private String apellido;
  private String tipoIdentificacion;
  private String identificacion;
  private String telefono;
  private String direccion;
  private String correoElectronico;
  private BigDecimal creditoMaximo;
  private BigDecimal cupoTotal;
  private String tipoCliente;

  public String getApellido() {
    return apellido;
  }

  public String getCorreoElectronico() {
    return correoElectronico;
  }

  public void setCorreoElectronico(String correoElectronico) {
    this.correoElectronico = correoElectronico;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getIdentificacion() {
    return identificacion;
  }

  public void setIdentificacion(String identificacion) {
    this.identificacion = identificacion;
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

  public BigDecimal getCreditoMaximo() {
    return creditoMaximo;
  }

  public void setCreditoMaximo(BigDecimal creditoMaximo) {
    this.creditoMaximo = creditoMaximo;
  }

  public String getTipoCliente() {
    return tipoCliente;
  }

  public void setTipoCliente(String tipoCliente) {
    this.tipoCliente = tipoCliente;
  }

  public String getTipoIdentificacion() {
    return tipoIdentificacion;
  }

  public void setTipoIdentificacion(String tipoIdentificacion) {
    this.tipoIdentificacion = tipoIdentificacion;
  }
}
