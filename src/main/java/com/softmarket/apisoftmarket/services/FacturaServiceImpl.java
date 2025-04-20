package com.softmarket.apisoftmarket.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softmarket.apisoftmarket.dto.FacturaDto;
import com.softmarket.apisoftmarket.dto.FacturaRequest;
import com.softmarket.apisoftmarket.entity.ExternalApiProperties;
import com.softmarket.apisoftmarket.entity.FacturaResponse;
import com.softmarket.apisoftmarket.entity.FactusTokenResponse;
import com.softmarket.apisoftmarket.exception.FacturaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.softmarket.apisoftmarket.mapper.FacturaMapper;
import reactor.core.publisher.Mono;

import java.util.Map;


@Service
public class FacturaServiceImpl implements FacturaService{

  private final AuthenticationService authenticationService;
  private final WebClient.Builder webClientBuilder;
  private final ExternalApiProperties externalApiProperties;
  private final FacturaMapper facturaMapper;
  private final ObjectMapper objectMapper;

  private static final Logger logger = LoggerFactory.getLogger(FacturaServiceImpl.class);

  public FacturaServiceImpl(AuthenticationService authenticationService,
                            WebClient.Builder webClientBuilder,
                            ExternalApiProperties externalApiProperties,
                            FacturaMapper facturaMapper, ObjectMapper objectMapper) {
    this.authenticationService = authenticationService;
    this.webClientBuilder = webClientBuilder;
    this.externalApiProperties = externalApiProperties;
    this.facturaMapper = facturaMapper;
    this.objectMapper = objectMapper;
  }

  @Override
  public ResponseEntity<?> crearfactura(FacturaRequest facturaRequest) throws JsonProcessingException {
    FactusTokenResponse factusTokenResponse = authenticationService.authenticationFactus();
    //Crear factura en Factus
    try {
      FacturaResponse responseFactus =
              webClientBuilder.build()
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

      assert responseFactus != null;
      FacturaDto facturaDto = facturaMapper.responseFactusToDto(responseFactus);
      logger.info("Token Response: ",factusTokenResponse.getAccess_token());
      logger.info("Factus Response: {} ",objectMapper.writeValueAsString(responseFactus));
      return ResponseEntity.ok(facturaDto);
    }catch (FacturaException e) {
      try {
        JsonNode errorJson = objectMapper.readTree(e.getBody());
        return ResponseEntity
                .status(e.getStatus())
                .body(errorJson);
      } catch (JsonProcessingException jsonEx) {
        logger.error("Error al parsear el cuerpo de error: {}", jsonEx.getMessage());
        return ResponseEntity
                .status(e.getStatus())
                .body(Map.of("status", e.getStatus().value(), "message", e.getBody()));
      }
    } catch (Exception e) {
      logger.error("💥 Error inesperado: {}", e.getMessage());
      return ResponseEntity
              .status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body(Map.of(
                      "status", 500,
                      "message", "Error interno: " + e.getMessage()
              ));
    }
  }
}
