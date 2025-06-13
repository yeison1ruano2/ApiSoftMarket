package com.softmarket.apisoftmarket.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="clientes")
public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nombre;
  private String apellido;
  private String identificacion;
  private String telefono;
  private String direccion;
  private BigDecimal creditoMaximo;
  private BigDecimal cupoTotal;
  @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
  @JsonBackReference
  private List<Venta> ventas = new ArrayList<>();

  public Cliente(Long id, String nombre, String apellido, String identificacion, String telefono, String direccion, BigDecimal creditoMaximo, BigDecimal cupoTotal) {
    this.id = id;
    this.nombre = nombre;
    this.apellido = apellido;
    this.identificacion = identificacion;
    this.telefono = telefono;
    this.direccion = direccion;
    this.creditoMaximo = creditoMaximo;
    this.cupoTotal = cupoTotal;
  }

  public Cliente() {
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public BigDecimal getCreditoMaximo() {
    return creditoMaximo;
  }

  public void setCreditoMaximo(BigDecimal creditoMaximo) {
    this.creditoMaximo = creditoMaximo;
  }

  public BigDecimal getCupoTotal() {
    return cupoTotal;
  }

  public void setCupoTotal(BigDecimal cupoTotal) {
    this.cupoTotal = cupoTotal;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getIdentificacion() {
    return identificacion;
  }

  public void setIdentificacion(String identificacion) {
    this.identificacion = identificacion;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public List<Venta> getVentas() {
    return ventas;
  }

  public void setVentas(List<Venta> ventas) {
    this.ventas = ventas;
  }
}
