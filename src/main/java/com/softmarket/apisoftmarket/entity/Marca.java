package com.softmarket.apisoftmarket.entity;

import jakarta.persistence.*;

@Entity
@Table(name="marcas")
public class Marca {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nombre;

  public Marca(Long id, String nombre) {
    this.id = id;
    this.nombre = nombre;
  }

  public Marca() {
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
