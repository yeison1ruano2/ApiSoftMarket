package com.softmarket.apisoftmarket.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class RangosEnumeracion {
  @Id
  private Long id;
  private String document;
  private String prefix;
  @Column(name = "`from`")
  private Long from;
  @Column(name = "`to`")
  private Long to;
  private Long current;
  private String resolution_number;
  private String start_date;
  private String end_date;
  private String technical_key;
  private Boolean is_expired;
  private Integer is_active;
  private String deleted_at;
  private String created_at;
  private String updated_at;

  public RangosEnumeracion() {
  }

  public RangosEnumeracion(Long id, String document, String prefix, long from, long to, long current, String resolution_number, String start_date, String end_date, String technical_key, boolean is_expired, int is_active, String deleted_at, String created_at, String updated_at) {
    this.id = id;
    this.document = document;
    this.prefix = prefix;
    this.from = from;
    this.to = to;
    this.current = current;
    this.resolution_number = resolution_number;
    this.start_date = start_date;
    this.end_date = end_date;
    this.technical_key = technical_key;
    this.is_expired = is_expired;
    this.is_active = is_active;
    this.deleted_at = deleted_at;
    this.created_at = created_at;
    this.updated_at = updated_at;
  }

  public String getCreated_at() {
    return created_at;
  }

  public void setCreated_at(String created_at) {
    this.created_at = created_at;
  }

  public Long getCurrent() {
    return current;
  }

  public void setCurrent(Long current) {
    this.current = current;
  }

  public String getDeleted_at() {
    return deleted_at;
  }

  public void setDeleted_at(String deleted_at) {
    this.deleted_at = deleted_at;
  }

  public String getDocument() {
    return document;
  }

  public void setDocument(String document) {
    this.document = document;
  }

  public String getEnd_date() {
    return end_date;
  }

  public void setEnd_date(String end_date) {
    this.end_date = end_date;
  }

  public Long getFrom() {
    return from;
  }

  public void setFrom(Long from) {
    this.from = from;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getIs_active() {
    return is_active;
  }

  public void setIs_active(Integer is_active) {
    this.is_active = is_active;
  }

  public Boolean getIs_expired() {
    return is_expired;
  }

  public void setIs_expired(Boolean is_expired) {
    this.is_expired = is_expired;
  }

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public String getResolution_number() {
    return resolution_number;
  }

  public void setResolution_number(String resolution_number) {
    this.resolution_number = resolution_number;
  }

  public String getStart_date() {
    return start_date;
  }

  public void setStart_date(String start_date) {
    this.start_date = start_date;
  }

  public String getTechnical_key() {
    return technical_key;
  }

  public void setTechnical_key(String technical_key) {
    this.technical_key = technical_key;
  }

  public Long getTo() {
    return to;
  }

  public void setTo(Long to) {
    this.to = to;
  }

  public String getUpdated_at() {
    return updated_at;
  }

  public void setUpdated_at(String updated_at) {
    this.updated_at = updated_at;
  }
}
