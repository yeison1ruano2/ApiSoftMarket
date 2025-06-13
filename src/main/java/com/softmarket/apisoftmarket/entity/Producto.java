package com.softmarket.apisoftmarket.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "productos")
public class Producto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nombre;
  @Column(unique = true)
  private String codigoBarras;

  @ManyToOne
  @JoinColumn(name="marca_id")
  private Marca marca;
  private BigDecimal precioVenta;
  private BigDecimal precioPorMayor;
  private Integer cantidadMinimaMayor;

  @ManyToOne
  @JoinColumn(name="categoria_id")
  private Categoria categoria;

  public Producto(Long id, String nombre,String codigoBarras,Marca marca,Categoria categoria, BigDecimal precioVenta,BigDecimal precioPorMayor,Integer cantidadMinimaMayor) {
    this.id = id;
    this.nombre = nombre;
    this.codigoBarras = codigoBarras;
    this.marca = marca;
    this.categoria = categoria;
    this.precioVenta = precioVenta;
    this.precioPorMayor = precioPorMayor;
    this.cantidadMinimaMayor = cantidadMinimaMayor;
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

  public Categoria getCategoria() {
    return categoria;
  }

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  public Marca getMarca() {
    return marca;
  }

  public void setMarca(Marca marca) {
    this.marca = marca;
  }

  public Integer getCantidadMinimaMayor() {
    return cantidadMinimaMayor;
  }

  public void setCantidadMinimaMayor(Integer cantidadMinimaMayor) {
    this.cantidadMinimaMayor = cantidadMinimaMayor;
  }

  public BigDecimal getPrecioPorMayor() {
    return precioPorMayor;
  }

  public void setPrecioPorMayor(BigDecimal precioPorMayor) {
    this.precioPorMayor = precioPorMayor;
  }

  public BigDecimal getPrecioVenta() {
    return precioVenta;
  }

  public void setPrecioVenta(BigDecimal precioVenta) {
    this.precioVenta = precioVenta;
  }
}
