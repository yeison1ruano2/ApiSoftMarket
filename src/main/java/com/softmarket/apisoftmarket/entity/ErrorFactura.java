package com.softmarket.apisoftmarket.entity;

import jakarta.persistence.*;

@Entity
public class ErrorFactura {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "TEXT")
  private String factura;

  private String message;

  public ErrorFactura(Long id, String factura, String message) {
    this.id = id;
    this.factura = factura;
    this.message = message;
  }

  public ErrorFactura(String factura, String message) {
    this.factura = factura;
    this.message = message;
  }

  public ErrorFactura() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFactura() {
    return factura;
  }

  public void setFactura(String factura) {
    this.factura = factura;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
