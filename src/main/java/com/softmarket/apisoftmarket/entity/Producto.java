package com.softmarket.apisoftmarket.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class Producto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nombre;
  @Column(unique = true)
  private String codigoBarras;
  private String marca;
  private float precioVenta;
  private float precioPorMayor;
  private float precioCredito;
  private int cantidadMinimaMayor;
  private String categoria;

  private int iva;

  public Producto(Long id, String nombre,String codigoBarras,String marca,String categoria, float precioVenta,float precioPorMayor,float precioCredito, int cantidadMinimaMayor,int iva) {
    this.id = id;
    this.nombre = nombre;
    this.codigoBarras = codigoBarras;
    this.marca = marca;
    this.categoria = categoria;
    this.precioVenta = precioVenta;
    this.precioPorMayor = precioPorMayor;
    this.cantidadMinimaMayor = cantidadMinimaMayor;
    this.precioCredito = precioCredito;
    this.iva = iva;
  }

  public Producto() {
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

  public String getCodigoBarras() {
    return codigoBarras;
  }

  public void setCodigoBarras(String codigoBarras) {
    this.codigoBarras = codigoBarras;
  }

  public String getCategoria() {
    return categoria;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  public String getMarca() {
    return marca;
  }

  public void setMarca(String marca) {
    this.marca = marca;
  }

  public int getCantidadMinimaMayor() {
    return cantidadMinimaMayor;
  }

  public void setCantidadMinimaMayor(int cantidadMinimaMayor) {
    this.cantidadMinimaMayor = cantidadMinimaMayor;
  }

  public float getPrecioPorMayor() {
    return precioPorMayor;
  }

  public void setPrecioPorMayor(float precioPorMayor) {
    this.precioPorMayor = precioPorMayor;
  }

  public float getPrecioVenta() {
    return precioVenta;
  }

  public void setPrecioVenta(float precioVenta) {
    this.precioVenta = precioVenta;
  }

  public int getIva() {
    return iva;
  }

  public void setIva(int iva) {
    this.iva = iva;
  }

  public float getPrecioCredito() {
    return precioCredito;
  }

  public void setPrecioCredito(float precioCredito) {
    this.precioCredito = precioCredito;
  }
}
