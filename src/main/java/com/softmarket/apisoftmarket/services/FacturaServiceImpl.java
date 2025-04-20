package com.softmarket.apisoftmarket.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softmarket.apisoftmarket.controller.FacturaController;
import com.softmarket.apisoftmarket.dto.FacturaDto;
import com.softmarket.apisoftmarket.dto.FacturaRequest;
import com.softmarket.apisoftmarket.entity.ExternalApiProperties;
import com.softmarket.apisoftmarket.entity.FacturaResponse;
import com.softmarket.apisoftmarket.entity.FactusTokenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.softmarket.apisoftmarket.mapper.FacturaMapper;

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
  public ResponseEntity<FacturaDto> crearfactura(FacturaRequest facturaRequest) throws JsonProcessingException {
    FactusTokenResponse factusTokenResponse = authenticationService.authenticationFactus();
    logger.info("Token Response: {} ",objectMapper.writeValueAsString(factusTokenResponse));
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
                      .bodyToMono(FacturaResponse.class)
                      .block();
      logger.info("Factus Response: {} ",objectMapper.writeValueAsString(responseFactus));
      assert responseFactus != null;
      FacturaDto facturaDto = facturaMapper.responseFactusToDto(responseFactus);
      return ResponseEntity.ok(facturaDto);
    }catch(Exception e){
      logger.info("Error Response: " + e);
      return ResponseEntity.ok(new FacturaDto(e.getMessage()));
    }
  }
}
