package com.softmarket.apisoftmarket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NumberingRangeResponse {

  private String prefix;
  private Integer from;
  @JsonProperty("to")
  private Integer to;
  private String resolution_number;
  private String start_date;
  private String end_date;
  private Integer months;

  public String getEnd_date() {
    return end_date;
  }

  public void setEnd_date(String end_date) {
    this.end_date = end_date;
  }

  public Integer getFrom() {
    return from;
  }

  public void setFrom(Integer from) {
    this.from = from;
  }

  public Integer getMonths() {
    return months;
  }

  public void setMonths(Integer months) {
    this.months = months;
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

  public Integer getTo() {
    return to;
  }

  public void setTo(Integer to) {
    this.to = to;
  }
}
