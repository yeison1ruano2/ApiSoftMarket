package com.softmarket.apisoftmarket.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class AuthorizationToken {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String token_type;
  private int expires_in;
  @Column(length = 3000)
  private String access_token;
  @Column(length = 3000)
  private String refresh_token;
  private LocalDateTime expiration_time;

  public AuthorizationToken(String access_token, int expires_in, Long id, String refresh_token, String token_type) {
    this.access_token = access_token;
    this.expires_in = expires_in;
    this.id = id;
    this.refresh_token = refresh_token;
    this.token_type = token_type;
  }

  public AuthorizationToken(Long id,String access_token, int expires_in, String refresh_token, String token_type,LocalDateTime timeColombia) {
    this.access_token = access_token;
    this.expires_in = expires_in;
    this.refresh_token = refresh_token;
    this.token_type = token_type;
    this.expiration_time = timeColombia;
    this.id = id;
  }

  public AuthorizationToken(String access_token, int expires_in, String refresh_token, String token_type,LocalDateTime timeColombia) {
    this.access_token = access_token;
    this.expires_in = expires_in;
    this.refresh_token = refresh_token;
    this.token_type = token_type;
    this.expiration_time = timeColombia;
  }

  public AuthorizationToken() {
  }

  public String getAccess_token() {
    return access_token;
  }

  public void setAccess_token(String access_token) {
    this.access_token = access_token;
  }

  public int getExpires_in() {
    return expires_in;
  }

  public void setExpires_in(int expires_in) {
    this.expires_in = expires_in;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRefresh_token() {
    return refresh_token;
  }

  public void setRefresh_token(String refresh_token) {
    this.refresh_token = refresh_token;
  }

  public String getToken_type() {
    return token_type;
  }

  public void setToken_type(String token_type) {
    this.token_type = token_type;
  }

  public LocalDateTime getExpiration_time() {
    return expiration_time;
  }

  public void setExpiration_time(LocalDateTime expiration_time) {
    this.expiration_time = expiration_time;
  }
}
