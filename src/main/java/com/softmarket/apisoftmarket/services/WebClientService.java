package com.softmarket.apisoftmarket.services;

import com.softmarket.apisoftmarket.dto.FacturaRequest;
import com.softmarket.apisoftmarket.entity.*;
import com.softmarket.apisoftmarket.exception.FacturaException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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

  public FacturaResponse enviarFacturaAFactus(FacturaRequest facturaRequest, FactusTokenResponse factusTokenResponse) {
    return webClientBuilder.build()
            .post()
            .uri(externalApiProperties.getFacturaUrl())
            .header(HttpHeaders.CONTENT_TYPE,"application/json")
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + factusTokenResponse.getAccess_token())
            .bodyValue(facturaRequest)
            .retrieve()
            .onStatus(HttpStatusCode::isError, response ->
                    response.bodyToMono(String.class).flatMap(errorBody -> {
                      HttpStatus statusCode = (HttpStatus) response.statusCode();
                      return Mono.error(new FacturaException(statusCode, errorBody));
                    })
            )
            .bodyToMono(FacturaResponse.class)
            .block();
  }
}
