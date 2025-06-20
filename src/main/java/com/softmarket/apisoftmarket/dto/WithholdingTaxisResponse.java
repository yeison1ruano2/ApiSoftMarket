package com.softmarket.apisoftmarket.dto;

import java.util.List;

public class WithholdingTaxisResponse {

  private String tribute_code;
  private String name;
  private String value;
  private List<RateResponse> rates;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<RateResponse> getRates() {
    return rates;
  }

  public void setRates(List<RateResponse> rates) {
    this.rates = rates;
  }

  public String getTribute_code() {
    return tribute_code;
  }

  public void setTribute_code(String tribute_code) {
    this.tribute_code = tribute_code;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
