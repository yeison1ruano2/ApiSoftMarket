package com.softmarket.apisoftmarket.dto;

import java.util.List;
import java.util.Map;

public class FacturaDto {

  private Integer status;
  private String number;
  private String reference_code;
  private String cufe;
  private String message;
  private Map<String, List<String>> errors;

  public FacturaDto(Integer status,String cufe, String number, String reference_code,String message,Map<String,List<String>> errors) {
    this.cufe = cufe;
    this.number = number;
    this.reference_code = reference_code;
    this.status = status;
    this.message = message;
    this.errors = errors;
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

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Map<String, List<String>> getErrors() {
    return errors;
  }

  public void setErrors(Map<String, List<String>> errors) {
    this.errors = errors;
  }
}
