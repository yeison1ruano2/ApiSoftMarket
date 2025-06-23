package com.softmarket.apisoftmarket.dto;

import com.softmarket.apisoftmarket.entity.RangosEnumeracion;

import java.util.List;

public class DataRangoEnumeracionFactusResponse {

  private List<RangosEnumeracion> data;

  public List<RangosEnumeracion> getData() {
    return data;
  }

  public void setData(List<RangosEnumeracion> data) {
    this.data = data;
  }
}
