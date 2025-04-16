package com.softmarket.ApiSoftMarket.dto;

public class FacturaDto {

  private String status;
  private String number;
  private String reference_code;
  private String cufe;
  private String message;

  public FacturaDto(String status,String cufe, String number, String reference_code) {
    this.cufe = cufe;
    this.number = number;
    this.reference_code = reference_code;
    this.status = status;
  }

  public FacturaDto(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
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

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
