package com.softmarket.apisoftmarket.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "external-api")
public class ExternalApiProperties {

  private String authUrl;
  private String facturaUrl;
  private String rangoEnumeracionUrl;
  private String descargarFacturaUrl;

  public String getDescargarFacturaUrl() {
    return descargarFacturaUrl;
  }

  public void setDescargarFacturaUrl(String descargarFacturaUrl) {
    this.descargarFacturaUrl = descargarFacturaUrl;
  }

  public String getRangoEnumeracionUrl() {
    return rangoEnumeracionUrl;
  }

  public void setRangoEnumeracionUrl(String rangoEnumeracionUrl) {
    this.rangoEnumeracionUrl = rangoEnumeracionUrl;
  }

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
