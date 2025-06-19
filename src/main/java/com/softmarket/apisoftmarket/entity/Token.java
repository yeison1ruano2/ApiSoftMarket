package com.softmarket.apisoftmarket.entity;

import jakarta.persistence.*;

@Entity
public class Token {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String token;
  private Boolean revocado;
  private Boolean expirado;

  @ManyToOne
  @JoinColumn(name="usuario_id")
  private Usuario usuario;

  public Token(Long id, String token, Boolean revocado, Boolean expirado, Usuario usuario) {
    this.id = id;
    this.token = token;
    this.revocado = revocado;
    this.expirado = expirado;
    this.usuario = usuario;
  }

  public Token() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Boolean getExpirado() {
    return expirado;
  }

  public void setExpirado(Boolean expirado) {
    this.expirado = expirado;
  }

  public Boolean getRevocado() {
    return revocado;
  }

  public void setRevocado(Boolean revocado) {
    this.revocado = revocado;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }
}
