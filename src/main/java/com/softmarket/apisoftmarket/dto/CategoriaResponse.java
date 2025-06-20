package com.softmarket.apisoftmarket.dto;

public class CategoriaResponse {
  private String id;
  private String nombre;

  public CategoriaResponse(String id, String nombre) {
    this.id = id;
    this.nombre = nombre;
  }

  public CategoriaResponse() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
}
