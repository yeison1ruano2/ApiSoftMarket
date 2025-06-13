package com.softmarket.apisoftmarket.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class Venta {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String codigoFactura;

  @ManyToOne
  @JoinColumn(name="cliente_id")
  @JsonManagedReference
  private Cliente cliente;

  @ManyToOne
  @JoinColumn(name="usuario_id")

  private Usuario usuario;

  private String metodoDePago;

  private Long fecha;

  private BigDecimal total;

  private boolean estado;

  @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JsonManagedReference
  private List<DetalleVenta> detalles;

  public Venta(Long id, String codigoFactura, Cliente cliente, Usuario usuario, String metodoDePago, Long fecha, BigDecimal total, boolean estado) {
    this.id = id;
    this.codigoFactura = codigoFactura;
    this.cliente = cliente;
    this.usuario = usuario;
    this.metodoDePago = metodoDePago;
    this.fecha = fecha;
    this.total = total;
    this.estado = estado;
  }

  public Venta() {
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public String getCodigoFactura() {
    return codigoFactura;
  }

  public void setCodigoFactura(String codigoFactura) {
    this.codigoFactura = codigoFactura;
  }

  public boolean isEstado() {
    return estado;
  }

  public void setEstado(boolean estado) {
    this.estado = estado;
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

  public String getMetodoDePago() {
    return metodoDePago;
  }

  public void setMetodoDePago(String metodoDePago) {
    this.metodoDePago = metodoDePago;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public List<DetalleVenta> getDetalles() {
    return detalles;
  }

  public void setDetalles(List<DetalleVenta> detalles) {
    this.detalles = detalles;
  }
}
