package com.softmarket.apisoftmarket.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="request_logs")
public class RequestLog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String ipCliente;
  private String urlFront;
  private String metodo;
  private String ruta;
  private String origin;
  private String referer;
  private LocalDateTime fechaHora;

  public String getUrlFront() {
    return urlFront;
  }

  public void setUrlFront(String urlFront) {
    this.urlFront = urlFront;
  }

  public RequestLog() {
  }

  public RequestLog(String ipCliente, String metodo, String ruta, String origin, String referer,String urlFront, LocalDateTime fechaHora) {
    this.ipCliente = ipCliente;
    this.metodo = metodo;
    this.ruta = ruta;
    this.origin = origin;
    this.urlFront = urlFront;
    this.referer = referer;
    this.fechaHora = fechaHora;
  }

  public LocalDateTime getFechaHora() {
    return fechaHora;
  }

  public void setFechaHora(LocalDateTime fechaHora) {
    this.fechaHora = fechaHora;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getIpCliente() {
    return ipCliente;
  }

  public void setIpCliente(String ipCliente) {
    this.ipCliente = ipCliente;
  }

  public String getMetodo() {
    return metodo;
  }

  public void setMetodo(String metodo) {
    this.metodo = metodo;
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public String getReferer() {
    return referer;
  }

  public void setReferer(String referer) {
    this.referer = referer;
  }

  public String getRuta() {
    return ruta;
  }

  public void setRuta(String ruta) {
    this.ruta = ruta;
  }
}
