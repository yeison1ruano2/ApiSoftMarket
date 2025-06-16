package com.softmarket.apisoftmarket.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="cuentas")
public class Cuenta {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private  String numeroCuenta;

  private BigDecimal valor;

  @ManyToOne
  @JoinColumn(name="usuario_id")
  private Usuario usuario;

  @ManyToOne
  @JoinColumn(name="venta_id")
  private Venta venta;

  private String comentarios;

  private Long fecha;

  private Boolean activa;

  @ManyToOne
  @JoinColumn(name="cliente_id",nullable = false)
  private Cliente cliente;

  public Cuenta(Long id, String numeroCuenta, Cliente cliente, Venta venta, BigDecimal valor, Usuario usuario, String comentarios, Long fecha, Boolean activa) {
    this.id = id;
    this.numeroCuenta = numeroCuenta;
    this.cliente = cliente;
    this.venta = venta;
    this.valor = valor;
    this.usuario = usuario;
    this.comentarios = comentarios;
    this.fecha = fecha;
    this.activa = activa;
  }

  public Venta getVenta() {
    return venta;
  }

  public void setVenta(Venta venta) {
    this.venta = venta;
  }

  public Cuenta() {
  }

  public Boolean getActiva() {
    return activa;
  }

  public void setActiva(Boolean activa) {
    this.activa = activa;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNumeroCuenta() {
    return numeroCuenta;
  }

  public void setNumeroCuenta(String numeroCuenta) {
    this.numeroCuenta = numeroCuenta;
  }

  public String getComentarios() {
    return comentarios;
  }

  public void setComentarios(String comentarios) {
    this.comentarios = comentarios;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public BigDecimal getValor() {
    return valor;
  }

  public void setValor(BigDecimal valor) {
    this.valor = valor;
  }

  public Long getFecha() {
    return fecha;
  }

  public void setFecha(Long fecha) {
    this.fecha = fecha;
  }
}
