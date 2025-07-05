package com.softmarket.apisoftmarket.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
public class Factura {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long fecha;
  private String number;
  private String reference_code;
  private String cufe;
  private String filename;
  @Column(name="pdf", columnDefinition = "text",length=100000)
  private String pdf;

  public Factura(String cufe, String number, String reference_code) {
    this.cufe = cufe;
    this.number = number;
    this.reference_code = reference_code;
    this.fecha = Instant.now().toEpochMilli();
  }

  public Factura(String cufe, String number, String reference_code, String filename, String pdf) {
    this.cufe = cufe;
    this.number = number;
    this.reference_code = reference_code;
    this.filename = filename;
    this.pdf = pdf;
    this.fecha = Instant.now().toEpochMilli();
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

  public Long getFecha() {
    return fecha;
  }

  public void setFecha(Long fecha) {
    this.fecha = fecha;
  }

  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  public String getPdf() {
    return pdf;
  }

  public void setPdf(String pdf) {
    this.pdf = pdf;
  }
}
