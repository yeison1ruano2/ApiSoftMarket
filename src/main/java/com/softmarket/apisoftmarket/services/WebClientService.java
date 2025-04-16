package com.softmarket.apisoftmarket.services;

import com.softmarket.apisoftmarket.entity.Authentication;
import com.softmarket.apisoftmarket.entity.AuthorizationToken;
import com.softmarket.apisoftmarket.entity.ExternalApiProperties;
import com.softmarket.apisoftmarket.entity.FactusTokenResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WebClientService {

  private final WebClient.Builder webClientBuilder;
  private final ExternalApiProperties externalApiProperties;

  public WebClientService(WebClient.Builder webClientBuilder, ExternalApiProperties externalApiProperties) {
    this.webClientBuilder = webClientBuilder;
    this.externalApiProperties = externalApiProperties;
  }

  public FactusTokenResponse authenticationCreate(Authentication authentication) {
    return webClientBuilder.build()
            .post()
            .uri(externalApiProperties.getAuthUrl())
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .body(BodyInserters
                    .fromFormData("grant_type","password")
                    .with("client_id",authentication.getClient_id())
                    .with("client_secret",authentication.getClient_secret())
                    .with("username",authentication.getUsername())
                    .with("password",authentication.getPassword()))
            .retrieve()
            .bodyToMono(FactusTokenResponse.class)
            .block();
  }

  public FactusTokenResponse authenticationRefresh(Authentication authentication, AuthorizationToken token) {
    return webClientBuilder.build()
            .post()
            .uri(externalApiProperties.getAuthUrl())
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .body(BodyInserters
                    .fromFormData("grant_type",authentication.getGran_type())
                    .with("client_id",authentication.getClient_id())
                    .with("client_secret",authentication.getClient_secret())
                    .with("refresh_token",token.getRefresh_token()))
            .retrieve()
            .bodyToMono(FactusTokenResponse.class)
            .block();
  }
}
