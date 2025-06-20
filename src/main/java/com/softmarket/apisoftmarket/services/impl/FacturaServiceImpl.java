package com.softmarket.apisoftmarket.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softmarket.apisoftmarket.dto.FacturaDto;
import com.softmarket.apisoftmarket.dto.FacturaRequest;
import com.softmarket.apisoftmarket.dto.FacturaResponse;
import com.softmarket.apisoftmarket.dto.FactusTokenResponse;
import com.softmarket.apisoftmarket.exception.FacturaException;
import com.softmarket.apisoftmarket.services.AuthenticationService;
import com.softmarket.apisoftmarket.services.FacturaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.softmarket.apisoftmarket.mapper.FacturaMapper;

@Service
public class FacturaServiceImpl implements FacturaService {

  private final AuthenticationService authenticationService;
  private final FacturaMapper facturaMapper;
  private final ObjectMapper objectMapper;
  private final WebClientService webClientService;

  private static final Logger logger = LoggerFactory.getLogger(FacturaServiceImpl.class);

  public FacturaServiceImpl(AuthenticationService authenticationService,FacturaMapper facturaMapper,
                            ObjectMapper objectMapper,WebClientService webClientService) {
    this.authenticationService = authenticationService;
    this.facturaMapper = facturaMapper;
    this.objectMapper = objectMapper;
    this.webClientService = webClientService;
  }

  @Override
  public ResponseEntity<?> crearfactura(FacturaRequest facturaRequest) throws JsonProcessingException {
    try {
      FactusTokenResponse factusTokenResponse = authenticationService.authenticationFactus();
      FacturaResponse responseFactus = webClientService.enviarFacturaAFactus(facturaRequest,factusTokenResponse);
      logFactura(factusTokenResponse,responseFactus);
      FacturaDto facturaDto = facturaMapper.responseFactusToDto(responseFactus);
      return ResponseEntity.ok(facturaDto);
    }catch (FacturaException e) {
      return manejarFacturaException(e, facturaRequest);
    }catch(Exception e){
      logger.error("💥 Error inesperado: {}", e.getMessage());
      FacturaDto facturaDto = facturaMapper.exceptionFactura500Save(e, facturaRequest);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(facturaDto);
    }
  }

  private ResponseEntity<FacturaDto> manejarFacturaException(FacturaException ex, FacturaRequest facturaRequest) throws JsonProcessingException {
    String message = extraerMensajeDeError(ex.getBody());
    FacturaDto facturaDto = facturaMapper.exceptionFacturaSave(ex,facturaRequest,message);
    return ResponseEntity.status(ex.getStatus()).body(facturaDto);
  }

  private String extraerMensajeDeError(String body) {
    try {
      JsonNode errorJson = objectMapper.readTree(body);
      return errorJson.has("message")? errorJson.get("message").asText() : errorJson.toString();
    }catch(JsonProcessingException e){
      logger.error("❌ Error al parsear el cuerpo de error: {}", e.getMessage());
      return "Error al parsear cuerpo de error: " + e.getMessage();
    }
  }

  private void logFactura(FactusTokenResponse factusTokenResponse, FacturaResponse responseFactus) {
    try {
      logger.info("🔐 Token Factus: {}", factusTokenResponse.getAccess_token());
      if(logger.isInfoEnabled()){
        logger.info("📦 Respuesta Factus: {}", objectMapper.writeValueAsString(responseFactus));
      }
    } catch (JsonProcessingException e) {
      logger.warn("No se pudo serializar la respuesta de Factus: {}", e.getMessage());
    }
  }
}
