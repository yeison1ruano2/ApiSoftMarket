package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.dto.*;
import com.softmarket.apisoftmarket.entity.*;
import com.softmarket.apisoftmarket.exception.Factura409Exception;
import com.softmarket.apisoftmarket.exception.Factura422Exception;
import com.softmarket.apisoftmarket.exception.FacturaException;
import io.netty.channel.unix.Errors;
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

import java.net.ConnectException;
import java.time.Duration;

@Service
public class WebClientService {

  private final WebClient webClientBuilder;
  private final ExternalApiProperties externalApiProperties;

  private static final Logger logger = LoggerFactory.getLogger(WebClientService.class);

  public WebClientService(WebClient.Builder webClientBuilder, ExternalApiProperties externalApiProperties) {
    this.webClientBuilder = webClientBuilder.build();
    this.externalApiProperties = externalApiProperties;
  }

  public FactusTokenResponse authenticationCreate(Authentication authentication) {
    return webClientBuilder
            .post()
            .uri(externalApiProperties.getAuthUrl())
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .body(BodyInserters
                    .fromFormData("grant_type", "password")
                    .with("client_id", authentication.getClient_id())
                    .with("client_secret", authentication.getClient_secret())
                    .with("username", authentication.getUsername())
                    .with("password", authentication.getPassword()))
            .retrieve()
            .bodyToMono(FactusTokenResponse.class)
            .retryWhen(Retry.fixedDelay(1,Duration.ofSeconds(2))
                    .filter(this::isRetryableError)
                    .doBeforeRetry(retrySignal -> logger.warn("🔄 Reintentando refresh token. Intento: {}, Error: {}",
                            retrySignal.totalRetries()+1,
                            retrySignal.failure().getMessage()))
                    .onRetryExhaustedThrow(((retryBackoffSpec, retrySignal) -> {
                      logger.error("❌ Error después de {} intentos: {}",
                              retrySignal.totalRetries()+1,
                              retrySignal.failure().getMessage());
                      return new RuntimeException("Error al refrescar token después de reintentos: " +
                              retrySignal.failure().getMessage(),
                              retrySignal.failure());
                    })))
            .doOnSuccess(response -> logger.info("✅ Token refrescado exitosamente"))
            .doOnError(error -> logger.error("💥 Error final en refresh token: {}", error.getMessage()))
            .block();
  }

  public FactusTokenResponse authenticationRefresh(Authentication authentication, AuthorizationToken token) {
    authentication.setGran_type("refresh_token");
    return webClientBuilder
            .post()
            .uri(externalApiProperties.getAuthUrl())
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .body(BodyInserters
                    .fromFormData("grant_type", authentication.getGran_type())
                    .with("client_id", authentication.getClient_id())
                    .with("client_secret", authentication.getClient_secret())
                    .with("refresh_token", token.getRefresh_token()))
            .retrieve()
            .bodyToMono(FactusTokenResponse.class)
            .retryWhen(Retry.fixedDelay(1, Duration.ofSeconds(2))
                    .filter(this::isRetryableError)
                    .doBeforeRetry(retrySignal -> logger.warn("🔄 Reintentando refresh token. Intento: {}, Error: {}",
                            retrySignal.totalRetries() + 1,
                            retrySignal.failure().getMessage()))
                    .onRetryExhaustedThrow((retryBackOffSpec,retrySignal)->{
                      logger.error("❌ Error después de {} intentos: {}",
                              retrySignal.totalRetries() + 1,
                              retrySignal.failure().getMessage());
                      return new RuntimeException("Error al refrescar token después de reintentos: " +
                              retrySignal.failure().getMessage(),
                              retrySignal.failure());
                    })
            )
            .doOnSuccess(response -> logger.info("✅ Token refrescado exitosamente"))
            .doOnError(error -> logger.error("💥 Error final en refresh token: {}", error.getMessage()))
            .block();
  }

  private boolean isRetryableError(Throwable throwable){
    String message = throwable.getMessage();
    if (message == null) return false;
    // Errores de conexión que justifican retry
    return throwable instanceof ConnectException || throwable instanceof java.net.SocketTimeoutException || throwable instanceof Errors.NativeIoException || message.contains("Connection reset") || message.contains("recvAddress") || message.contains("Connection refused") || message.contains("timeout") || message.contains("broken pipe");
  }

  public FacturaResponse enviarFacturaAFactus(FacturaRequest facturaRequest, String accessToken) {
    return webClientBuilder
            .post()
            .uri(externalApiProperties.getFacturaUrl())
            .header(HttpHeaders.CONTENT_TYPE, "application/json")
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
            .bodyValue(facturaRequest)
            .retrieve()
            .onStatus(HttpStatusCode::isError, response -> {
              if(response.statusCode().value() == 409){
                logger.info("Error 409");
                return response.bodyToMono(FacturaError409Response.class)
                        .flatMap(error -> {
                          String message = error.getMessage();
                          return Mono.error(new Factura409Exception(
                                  (HttpStatus) response.statusCode(),
                                  message
                          ));
                        });
              }else if(response.statusCode().value() == 422){
                logger.info("Error 422");
                return response.bodyToMono(FacturaError422Response.class)
                        .flatMap(error -> Mono.error(new Factura422Exception(
                                HttpStatus.UNPROCESSABLE_ENTITY,
                                error.getMessage(),
                                error.getData().getErrors()
                        )));
              }else{
                logger.info("Otro error");
                 return response.bodyToMono(String.class)
                        .flatMap(body -> Mono.error(new FacturaException(
                                body,
                                (HttpStatus) response.statusCode()
                        )));
              }})
            .bodyToMono(FacturaResponse.class)
            .block();
  }

  public DataRangoEnumeracionFactusResponse buscarCrearRangoEnumeracion(String token) {
    return webClientBuilder
            .get()
            .uri(externalApiProperties.getRangoEnumeracionUrl())
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .retrieve()
            .bodyToMono(DataRangoEnumeracionFactusResponse.class)
            .block();
  }

  public FacturaPdfFactusResponse descargarPdfFactus(String number, String accessToken) {
    return webClientBuilder
            .get()
            .uri(externalApiProperties.getDescargarFacturaUrl()+"/{number}",number)
            .header(HttpHeaders.AUTHORIZATION,"Bearer " + accessToken)
            .retrieve()
            .bodyToMono(FacturaPdfFactusResponse.class)
            .block();
  }
}