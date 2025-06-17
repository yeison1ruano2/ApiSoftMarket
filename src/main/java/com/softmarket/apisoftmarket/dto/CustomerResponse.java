package com.softmarket.apisoftmarket.dto;

import com.softmarket.apisoftmarket.entity.LegalOrganizationResponse;
import com.softmarket.apisoftmarket.entity.MunicipalityResponse;
import com.softmarket.apisoftmarket.entity.TributeResponse;

public class CustomerResponse {

  private String identification;
  private String dv;
  private String graphic_representation_name;
  private String trade_name;
  private String company;
  private String names;
  private String address;
  private String email;
  private String phone;
  private LegalOrganizationResponse legal_organization;
  private TributeResponse tribute;
  private MunicipalityResponse municipality;

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getDv() {
    return dv;
  }

  public void setDv(String dv) {
    this.dv = dv;
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

  public String getIdentification() {
    return identification;
  }

  public void setIdentification(String identification) {
    this.identification = identification;
  }

  public LegalOrganizationResponse getLegal_organization() {
    return legal_organization;
  }

  public void setLegal_organization(LegalOrganizationResponse legal_organization) {
    this.legal_organization = legal_organization;
  }

  public MunicipalityResponse getMunicipality() {
    return municipality;
  }

  public void setMunicipality(MunicipalityResponse municipalityResponse) {
    this.municipality = municipalityResponse;
  }

  public String getNames() {
    return names;
  }

  public void setNames(String names) {
    this.names = names;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getTrade_name() {
    return trade_name;
  }

  public void setTrade_name(String trade_name) {
    this.trade_name = trade_name;
  }

  public TributeResponse getTribute() {
    return tribute;
  }

  public void setTribute(TributeResponse tribute) {
    this.tribute = tribute;
  }
}
