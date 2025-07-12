package com.softmarket.apisoftmarket.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "authorization_tokens")
public class AuthorizationToken {
  @Id
  private Long id;
  private String authenticationId;
  private String token_type;
  private Integer expires_in;
  private String access_token;
  private String refresh_token;
  private LocalDateTime expiration_time;
  private String rangoEnumeracionVenta;

  public AuthorizationToken(Long id,String access_token, Integer expires_in, String refresh_token, String token_type,LocalDateTime timeColombia,String authenticacionId) {
    this.access_token = access_token;
    this.expires_in = expires_in;
    this.refresh_token = refresh_token;
    this.token_type = token_type;
    this.expiration_time = timeColombia;
    this.id = id;
    this.authenticationId = authenticacionId;
  }


  public AuthorizationToken(String access_token, Integer expires_in, String refresh_token, String token_type,LocalDateTime timeColombia,String authenticationId) {
    this.access_token = access_token;
    this.expires_in = expires_in;
    this.refresh_token = refresh_token;
    this.token_type = token_type;
    this.expiration_time = timeColombia;
    this.authenticationId = authenticationId;
  }

  public AuthorizationToken() {
  }

  public String getAccess_token() {
    return access_token;
  }

  public void setAccess_token(String access_token) {
    this.access_token = access_token;
  }

  public Integer getExpires_in() {
    return expires_in;
  }

  public void setExpires_in(Integer expires_in) {
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

  public String getAuthenticationId() {
    return authenticationId;
  }

  public void setAuthenticationId(String authenticationId) {
    this.authenticationId = authenticationId;
  }

  public String getRangoEnumeracionVenta() {
    return rangoEnumeracionVenta;
  }

  public void setRangoEnumeracionVenta(String rangoEnumeracionVenta) {
    this.rangoEnumeracionVenta = rangoEnumeracionVenta;
  }

}
