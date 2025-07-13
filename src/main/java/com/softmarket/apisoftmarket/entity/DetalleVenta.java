package com.softmarket.apisoftmarket.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class DetalleVenta {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "venta_id", nullable = false)
  @JsonBackReference
  private Venta venta;

  @ManyToOne
  @JoinColumn(name = "producto_id", nullable = false)
  private Producto producto;

  private int cantidad;

  private float precioUnitario;

  private float subtotal;

  public DetalleVenta(Long id, Venta venta, Producto producto, int cantidad, float precioUnitario, float subtotal) {
    this.id = id;
    this.venta = venta;
    this.producto = producto;
    this.cantidad = cantidad;
    this.precioUnitario = precioUnitario;
    this.subtotal = subtotal;
  }

  public DetalleVenta() {
  }

  public int getCantidad() {
    return cantidad;
  }

  public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public float getPrecioUnitario() {
    return precioUnitario;
  }

  public void setPrecioUnitario(float precioUnitario) {
    this.precioUnitario = precioUnitario;
  }

  public Producto getProducto() {
    return producto;
  }

  public void setProducto(Producto producto) {
    this.producto = producto;
  }

  public float getSubtotal() {
    return subtotal;
  }

  public void setSubtotal(float subtotal) {
    this.subtotal = subtotal;
  }

  public Venta getVenta() {
    return venta;
  }

  public void setVenta(Venta venta) {
    this.venta = venta;
  }
}
