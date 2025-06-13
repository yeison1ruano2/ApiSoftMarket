package com.softmarket.apisoftmarket.dto;

import java.math.BigDecimal;
import java.util.List;

public class VentaRequest {
  private String codigoFactura;
  private String clienteId;
  private String usuarioId;
  private String metodoDePago;
  private BigDecimal total;
  private List<DetalleVentaRequest> detalles;

  public String getClienteId() {
    return clienteId;
  }

  public void setClienteId(String clienteId) {
    this.clienteId = clienteId;
  }

  public String getCodigoFactura() {
    return codigoFactura;
  }

  public void setCodigoFactura(String codigoFactura) {
    this.codigoFactura = codigoFactura;
  }

  public String getMetodoDePago() {
    return metodoDePago;
  }

  public void setMetodoDePago(String metodoDePago) {
    this.metodoDePago = metodoDePago;
  }

  public List<DetalleVentaRequest> getDetalles() {
    return detalles;
  }

  public void setDetalles(List<DetalleVentaRequest> detalles) {
    this.detalles = detalles;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }

  public String getUsuarioId() {
    return usuarioId;
  }

  public void setUsuarioId(String usuarioId) {
    this.usuarioId = usuarioId;
  }
}
