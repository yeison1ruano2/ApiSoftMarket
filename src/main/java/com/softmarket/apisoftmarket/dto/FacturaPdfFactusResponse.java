package com.softmarket.apisoftmarket.dto;

public class FacturaPdfFactusResponse {

  private String status;
  private String message;
  private Data data;

  public static class Data {
    private String file_name;
    private String pdf_base_64_encoded;

    public String getFile_name() {
      return file_name;
    }

    public void setFile_name(String file_name) {
      this.file_name = file_name;
    }

    public String getPdf_base_64_encoded() {
      return pdf_base_64_encoded;
    }

    public void setPdf_base_64_encoded(String pdf_base_64_encoded) {
      this.pdf_base_64_encoded = pdf_base_64_encoded;
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
