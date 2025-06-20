package com.softmarket.apisoftmarket.dto;

public class FactusTokenResponse {
  private String token_type;
  private int expires_in;
  private String access_token;
  private String refresh_token;

  public FactusTokenResponse(String access_token, int expires_in, String refresh_token, String token_type) {
    this.access_token = access_token;
    this.expires_in = expires_in;
    this.refresh_token = refresh_token;
    this.token_type = token_type;
  }

  public FactusTokenResponse() {
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
}
