package com.softmarket.apisoftmarket.dto;

import java.util.List;
import java.util.Map;

public class FacturaError422Response {

  private String status;
  private String message;
  private Data data;

  public static class Data {
    private String message;
    private Map<String, List<String>> errors;

    public Map<String, List<String>> getErrors() {
      return errors;
    }

    public void setErrors(Map<String, List<String>> errors) {
      this.errors = errors;
    }

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }
  }

  public Data getData() {
    return data;
  }

  public void setData(Data data) {
    this.data = data;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

}
