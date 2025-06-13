package com.softmarket.apisoftmarket.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
public class Inventario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name="producto_id")
  private Producto producto;

  private Integer cantidadActual;

  private Integer stockMinimo;

  private Long ultimaActualizacion;

  public Inventario(Long id, Producto producto, Integer cantidadActual, Integer stockMinimo, Instant ultimaActualizacion) {
    this.id = id;
    this.producto = producto;
    this.cantidadActual = cantidadActual;
    this.stockMinimo = stockMinimo;
    this.ultimaActualizacion = ultimaActualizacion.toEpochMilli();
  }

  public Inventario(Producto producto, Integer cantidadActual, Integer stockMinimo) {
    this.producto = producto;
    this.cantidadActual = cantidadActual;
    this.stockMinimo = stockMinimo;
    this.ultimaActualizacion = Instant.now().toEpochMilli();
  }

  public Inventario() {
  }

  public Integer getCantidadActual() {
    return cantidadActual;
  }

  public void setCantidadActual(Integer cantidadActual) {
    this.cantidadActual = cantidadActual;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Producto getProducto() {
    return producto;
  }

  public void setProducto(Producto producto) {
    this.producto = producto;
  }

  public Integer getStockMinimo() {
    return stockMinimo;
  }

  public void setStockMinimo(Integer stockMinimo) {
    this.stockMinimo = stockMinimo;
  }

  public Long getUltimaActualizacion() {
    return ultimaActualizacion;
  }

  public void setUltimaActualizacion(Long ultimaActualizacion) {
    this.ultimaActualizacion = ultimaActualizacion;
  }
}
