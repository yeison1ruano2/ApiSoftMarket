package com.softmarket.apisoftmarket.dto;

import java.math.BigDecimal;
import java.util.List;

public class VentaResponse {
  private String codigoFactura;
  private ClienteResponse cliente;
  private UsuarioResponse usuario;
  private Long fecha;
  private BigDecimal total;
  private List<DetalleVentaResponse> detalles;

  public VentaResponse(String codigoFactura, ClienteResponse cliente, UsuarioResponse usuario, Long fecha, BigDecimal total, List<DetalleVentaResponse> detalles) {
    this.codigoFactura = codigoFactura;
    this.cliente = cliente;
    this.usuario = usuario;
    this.fecha = fecha;
    this.total = total;
    this.detalles = detalles;
  }

  public ClienteResponse getCliente() {
    return cliente;
  }

  public void setCliente(ClienteResponse cliente) {
    this.cliente = cliente;
  }

  public String getCodigoFactura() {
    return codigoFactura;
  }

  public void setCodigoFactura(String codigoFactura) {
    this.codigoFactura = codigoFactura;
  }

  public List<DetalleVentaResponse> getDetalles() {
    return detalles;
  }

  public void setDetalles(List<DetalleVentaResponse> detalles) {
    this.detalles = detalles;
  }

  public Long getFecha() {
    return fecha;
  }

  public void setFecha(Long fecha) {
    this.fecha = fecha;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }

  public UsuarioResponse getUsuario() {
    return usuario;
  }

  public void setUsuario(UsuarioResponse usuario) {
    this.usuario = usuario;
  }
}
