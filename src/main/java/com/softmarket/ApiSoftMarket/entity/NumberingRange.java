package com.softmarket.ApiSoftMarket.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NumberingRange {

  private String prefix;
  private int from;
  @JsonProperty("to")
  private int myto;
  private String resolution_number;
  private String start_date;
  private String end_date;
  private int months;

  public String getEnd_date() {
    return end_date;
  }

  public void setEnd_date(String end_date) {
    this.end_date = end_date;
  }

  public int getFrom() {
    return from;
  }

  public void setFrom(int from) {
    this.from = from;
  }

  public int getMonths() {
    return months;
  }

  public void setMonths(int months) {
    this.months = months;
  }

  public int getMyto() {
    return myto;
  }

  public void setMyto(int myto) {
    this.myto = myto;
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
}
