package com.softmarket.apisoftmarket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CodigosTributosClientes {
  @Id
  private Long id;
  private String nombre;

  public CodigosTributosClientes(Long id, String nombre) {
    this.id = id;
    this.nombre = nombre;
  }

  public CodigosTributosClientes() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
}
