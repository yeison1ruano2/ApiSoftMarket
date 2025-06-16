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
  @ManyToOne
  @JoinColumn(name = "tipo_identificacion_id")
  private TipoIdentificacion tipoIdentificacion;
  private String identificacion;
  private String telefono;
  private String direccion;
  private String correoElectronico;
  private BigDecimal creditoMaximo;
  private BigDecimal cupoTotal;
  @ManyToOne
  @JoinColumn(name = "tipo_cliente_id")
  private TipoCliente tipoCliente;
  @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
  @JsonBackReference
  private List<Venta> ventas = new ArrayList<>();
  @OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL)
  private List<Cuenta> cuentas = new ArrayList<>();

  public Cliente(Long id, String nombre, String apellido, TipoIdentificacion tipoIdentificacion, String identificacion, String telefono, String direccion, String correoElectronico, BigDecimal creditoMaximo, BigDecimal cupoTotal, TipoCliente tipoCliente, List<Venta> ventas, List<Cuenta> cuentas) {
    this.id = id;
    this.nombre = nombre;
    this.apellido = apellido;
    this.tipoIdentificacion = tipoIdentificacion;
    this.identificacion = identificacion;
    this.telefono = telefono;
    this.direccion = direccion;
    this.correoElectronico = correoElectronico;
    this.creditoMaximo = creditoMaximo;
    this.cupoTotal = cupoTotal;
    this.tipoCliente = tipoCliente;
    this.ventas = ventas;
    this.cuentas = cuentas;
  }

  public Cliente(String nombre, String apellido, TipoIdentificacion tipoIdentificacion, String identificacion, String telefono, String direccion, String correoElectronico, BigDecimal creditoMaximo, BigDecimal cupoTotal, TipoCliente tipoCliente) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.tipoIdentificacion = tipoIdentificacion;
    this.identificacion = identificacion;
    this.telefono = telefono;
    this.direccion = direccion;
    this.correoElectronico = correoElectronico;
    this.creditoMaximo = creditoMaximo;
    this.cupoTotal = cupoTotal;
    this.tipoCliente = tipoCliente;
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

  public String getCorreoElectronico() {
    return correoElectronico;
  }

  public void setCorreoElectronico(String correoElectronico) {
    this.correoElectronico = correoElectronico;
  }

  public TipoIdentificacion getTipoIdentificacion() {
    return tipoIdentificacion;
  }

  public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
    this.tipoIdentificacion = tipoIdentificacion;
  }

  public List<Cuenta> getCuentas() {
    return cuentas;
  }

  public void setCuentas(List<Cuenta> cuentas) {
    this.cuentas = cuentas;
  }

  public TipoCliente getTipoCliente() {
    return tipoCliente;
  }

  public void setTipoCliente(TipoCliente tipoCliente) {
    this.tipoCliente = tipoCliente;
  }
}
