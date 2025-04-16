package com.softmarket.apisoftmarket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Authentication {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String client_id;

  private String client_secret;

  private String username;

  private String password;

  private String gran_type;

  public Authentication() {
  }

  public Authentication(String client_id, String client_secret, String gran_type, Long id, String password, String username) {
    this.client_id = client_id;
    this.client_secret = client_secret;
    this.gran_type = gran_type;
    this.id = id;
    this.password = password;
    this.username = username;
  }

  public String getClient_id() {
    return client_id;
  }

  public void setClient_id(String client_id) {
    this.client_id = client_id;
  }

  public String getClient_secret() {
    return client_secret;
  }

  public void setClient_secret(String client_secret) {
    this.client_secret = client_secret;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getGran_type() {
    return gran_type;
  }

  public void setGran_type(String gran_type) {
    this.gran_type = gran_type;
  }
}
