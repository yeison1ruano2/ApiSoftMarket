package com.softmarket.ApiSoftMarket.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "external-api")
public class ExternalApiProperties {

  private String authUrl;
  private String facturaUrl;

  public String getFacturaUrl() {
    return facturaUrl;
  }

  public void setFacturaUrl(String facturaUrl) {
    this.facturaUrl = facturaUrl;
  }

  public String getAuthUrl() {
    return authUrl;
  }

  public void setAuthUrl(String authUrl) {
    this.authUrl = authUrl;
  }
}
