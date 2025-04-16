package com.softmarket.ApiSoftMarket.entity;

public class CompanyResponse {
  private String url_logo;
  private String nit;
  private String dv;
  private String company;
  private String name;
  private String graphic_representation_name;
  private String registration_code;
  private String economic_activity;
  private String phone;
  private String email;
  private String direction;
  private String municipality;

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }

  public String getDv() {
    return dv;
  }

  public void setDv(String dv) {
    this.dv = dv;
  }

  public String getEconomic_activity() {
    return economic_activity;
  }

  public void setEconomic_activity(String economic_activity) {
    this.economic_activity = economic_activity;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getGraphic_representation_name() {
    return graphic_representation_name;
  }

  public void setGraphic_representation_name(String graphic_representation_name) {
    this.graphic_representation_name = graphic_representation_name;
  }

  public String getMunicipality() {
    return municipality;
  }

  public void setMunicipality(String municipality) {
    this.municipality = municipality;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNit() {
    return nit;
  }

  public void setNit(String nit) {
    this.nit = nit;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getRegistration_code() {
    return registration_code;
  }

  public void setRegistration_code(String registration_code) {
    this.registration_code = registration_code;
  }

  public String getUrl_logo() {
    return url_logo;
  }

  public void setUrl_logo(String url_logo) {
    this.url_logo = url_logo;
  }
}
