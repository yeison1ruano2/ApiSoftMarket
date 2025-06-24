package com.softmarket.apisoftmarket.dto;

public class AuthFacturaRequest {
  private String auth_id;
  private FacturaRequest data;

  public FacturaRequest getData() {
    return data;
  }

  public void setData(FacturaRequest data) {
    this.data = data;
  }

  public String getAuth_id() {
    return auth_id;
  }

  public void setAuth_id(String auth_id) {
    this.auth_id = auth_id;
  }
}
