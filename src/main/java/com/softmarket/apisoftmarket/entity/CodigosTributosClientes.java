package com.softmarket.apisoftmarket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class CodigosTributosClientes {
  @Id
  private Long id;
  private String nombre;
  private BigDecimal porcentaje;

  public CodigosTributosClientes(Long id, String nombre, BigDecimal porcentaje) {
    this.id = id;
    this.nombre = nombre;
    this.porcentaje = porcentaje;
  }

  public CodigosTributosClientes() {
  }

  public BigDecimal getPorcentaje() {
    return porcentaje;
  }

  public void setPorcentaje(BigDecimal porcentaje) {
    this.porcentaje = porcentaje;
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
