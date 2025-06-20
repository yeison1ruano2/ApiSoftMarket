package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.dto.FacturaRequest;
import com.softmarket.apisoftmarket.dto.FacturaResponse;
import com.softmarket.apisoftmarket.dto.FactusTokenResponse;
import com.softmarket.apisoftmarket.entity.*;
import com.softmarket.apisoftmarket.exception.FacturaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Service
public class WebClientService {

  private final WebClient webClient;
  private final ExternalApiProperties externalApiProperties;
  private static final Logger logger = LoggerFactory.getLogger(WebClientService.class);

  public WebClientService(WebClient.Builder webClientBuilder, ExternalApiProperties externalApiProperties) {
    this.webClient = webClientBuilder.build();
    this.externalApiProperties = externalApiProperties;
  }

  public FactusTokenResponse authenticationCreate(Authentication authentication) {
    return sendAuthRequest(
            BodyInserters
                    .fromFormData("grant_type","password")
                    .with("client_id",authentication.getClient_id())
                    .with("client_Secret", authentication.getClient_secret())
                    .with("username",authentication.getUsername())
                    .with("password", authentication.getPassword())
    ).block();
  }

  public FactusTokenResponse authenticationRefresh(Authentication authentication, AuthorizationToken token) {
    return sendAuthRequest(
            BodyInserters
                    .fromFormData("grant_type",authentication.getGran_type())
                    .with("client_id", authentication.getClient_id())
                    .with("client_secret", authentication.getClient_secret())
                    .with("refresh_token",token.getRefresh_token())
    ).block();
  }

  public FacturaResponse enviarFacturaAFactus(FacturaRequest facturaRequest, FactusTokenResponse factusTokenResponse) {
    return webClient
            .post()
            .uri(externalApiProperties.getFacturaUrl())
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + factusTokenResponse.getAccess_token())
            .bodyValue(facturaRequest)
            .retrieve()
            .onStatus(HttpStatusCode::isError, this::handleError)
            .bodyToMono(FacturaResponse.class)
            .block();
  }

  private Mono<? extends Throwable> handleError(org.springframework.web.reactive.function.client.ClientResponse response){
    return response.bodyToMono(String.class)
            .flatMap(errorBody -> {
              HttpStatus status = (HttpStatus) response.statusCode();
              return Mono.error(new FacturaException(status,errorBody));
            });
  }

  private Mono<FactusTokenResponse> sendAuthRequest(BodyInserters.FormInserter<String> body){
    return webClient
            .post()
            .uri(externalApiProperties.getAuthUrl())
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .body(body)
            .retrieve()
            .bodyToMono(FactusTokenResponse.class)
            .retryWhen(Retry.backoff(2, Duration.ofSeconds(2)))
            .doOnNext(token -> logger.debug("Token obtenido exitosamente"))
            .doOnError(error -> logger.error("Error en autenticacion: {}",error.getMessage()));
  }
}
