package com.softmarket.apisoftmarket.dto;

public class UserRequest {
  private String nombre;
  private String apellido;
  private String identificacion;
  private String correo;
  private String telefono;
  private String username;
  private String password;
  private Role role;

  public UserRequest() {
  }

  public UserRequest(String apellido, String correo, String identificacion, String nombre, String password,
      String telefono, String username) {
    this.apellido = apellido;
    this.correo = correo;
    this.identificacion = identificacion;
    this.nombre = nombre;
    this.password = password;
    this.telefono = telefono;
    this.username = username;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
