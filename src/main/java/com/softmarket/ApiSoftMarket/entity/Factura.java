package com.softmarket.ApiSoftMarket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Factura {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String number;
  private String reference_code;
  private String cufe;

  public Factura(String cufe, Long id, String number, String reference_code) {
    this.cufe = cufe;
    this.id = id;
    this.number = number;
    this.reference_code = reference_code;
  }

  public Factura(String cufe, String number, String reference_code) {
    this.cufe = cufe;
    this.number = number;
    this.reference_code = reference_code;
  }

  public Factura() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCufe() {
    return cufe;
  }

  public void setCufe(String cufe) {
    this.cufe = cufe;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getReference_code() {
    return reference_code;
  }

  public void setReference_code(String reference_code) {
    this.reference_code = reference_code;
  }
}
