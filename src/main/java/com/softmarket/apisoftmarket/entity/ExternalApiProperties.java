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
  private String githubToken;
  private String githubOwner;
  private String githubRepo;
  private String nameAuthorizationGoogle;
  private String urlGithub;

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

  public String getGithubOwner() {
    return githubOwner;
  }

  public void setGithubOwner(String githubOwner) {
    this.githubOwner = githubOwner;
  }

  public String getGithubRepo() {
    return githubRepo;
  }

  public void setGithubRepo(String githubRepo) {
    this.githubRepo = githubRepo;
  }

  public String getGithubToken() {
    return githubToken;
  }

  public void setGithubToken(String githubToken) {
    this.githubToken = githubToken;
  }

  public String getNameAuthorizationGoogle() {
    return nameAuthorizationGoogle;
  }

  public void setNameAuthorizationGoogle(String nameAuthorizationGoogle) {
    this.nameAuthorizationGoogle = nameAuthorizationGoogle;
  }

  public String getUrlGithub() {
    return urlGithub;
  }

  public void setUrlGithub(String urlGithub) {
    this.urlGithub = urlGithub;
  }
}
