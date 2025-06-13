package com.softmarket.apisoftmarket.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
public class MovimientoInventario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "inventario_id")
  private Inventario inventario;

  private Long fecha;

  private String tipoMovimiento; // ENTRADA o SALIDA

  private Integer cantidad;

  private String observacion;

  public MovimientoInventario(Long id, Inventario inventario,String tipoMovimiento, Integer cantidad) {
    this.id = id;
    this.inventario = inventario;
    this.fecha = Instant.now().toEpochMilli();
    this.tipoMovimiento = tipoMovimiento;
    this.cantidad = cantidad;
  }

  public MovimientoInventario() {
  }

  public Integer getCantidad() {
    return cantidad;
  }

  public void setCantidad(Integer cantidad) {
    this.cantidad = cantidad;
  }

  public Long getFecha() {
    return fecha;
  }

  public void setFecha(Long fecha) {
    this.fecha = fecha;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Inventario getInventario() {
    return inventario;
  }

  public void setInventario(Inventario inventario) {
    this.inventario = inventario;
  }

  public String getObservacion() {
    return observacion;
  }

  public void setObservacion(String observacion) {
    this.observacion = observacion;
  }

  public String getTipoMovimiento() {
    return tipoMovimiento;
  }

  public void setTipoMovimiento(String tipoMovimiento) {
    this.tipoMovimiento = tipoMovimiento;
  }
}
