package com.softmarket.apisoftmarket.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softmarket.apisoftmarket.dto.*;
import com.softmarket.apisoftmarket.entity.Authentication;
import com.softmarket.apisoftmarket.entity.AuthorizationToken;
import com.softmarket.apisoftmarket.entity.Factura;
import com.softmarket.apisoftmarket.exception.FacturaException;
import com.softmarket.apisoftmarket.mapper.AuthenticationMapper;
import com.softmarket.apisoftmarket.repository.FacturaRepository;
import com.softmarket.apisoftmarket.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.softmarket.apisoftmarket.mapper.FacturaMapper;

import java.time.LocalDateTime;

@Service
public class FacturaServiceImpl implements FacturaService {

  private final AuthenticationService authenticationService;
  private final AuthorizationTokenService authorizationTokenService;
  private final FacturaMapper facturaMapper;
  private final ObjectMapper objectMapper;
  private final WebClientService webClientService;
  private final RangoEnumeracionService rangoEnumeracionService;
  private final AuthenticationMapper authenticationMapper;
  private final FacturaRepository facturaRepository;

  private static final Logger logger = LoggerFactory.getLogger(FacturaServiceImpl.class);

  public FacturaServiceImpl(AuthenticationService authenticationService, AuthorizationTokenService authorizationTokenService, FacturaMapper facturaMapper,
                            ObjectMapper objectMapper, WebClientService webClientService, RangoEnumeracionService rangoEnumeracionService, AuthenticationMapper authenticationMapper, FacturaRepository facturaRepository) {
    this.authenticationService = authenticationService;
    this.authorizationTokenService = authorizationTokenService;
    this.facturaMapper = facturaMapper;
    this.objectMapper = objectMapper;
    this.webClientService = webClientService;
    this.authenticationMapper = authenticationMapper;
    this.rangoEnumeracionService = rangoEnumeracionService;
    this.facturaRepository = facturaRepository;
  }

  @Override
  public ResponseEntity<?> crearfactura(FacturaRequest facturaRequest) throws JsonProcessingException {
    try {
      Factura factura = facturaRepository.findByReferenceCode(facturaRequest.getReference_code()).orElse(null);
      if(factura != null){
        return ResponseEntity.status(HttpStatus.OK).body(facturaMapper.entityToDto(factura));
      }
      String accessToken = authenticationService.obtenerToken();
      Integer idventa = rangoEnumeracionService.rangoEnumeracionVenta().intValue();
      facturaRequest.setNumbering_range_id(idventa);
      FacturaResponse responseFactus = webClientService.enviarFacturaAFactus(facturaRequest,accessToken);
      FacturaDto facturaDto = facturaMapper.responseFactusToDto(responseFactus);
      return ResponseEntity.ok(facturaDto);
    }catch (FacturaException e) {
      return manejarFacturaException(e, facturaRequest);
    }catch(Exception e){
      logErrorInesperado(e);
      FacturaDto facturaDto = facturaMapper.exceptionFactura500Save(e, facturaRequest);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(facturaDto);
    }
  }

  @Override
  public ResponseEntity<?> crearFacturaV2(String authId, FacturaRequest data) {
    try{
       AuthorizationToken authorizationToken = authorizationTokenService.obtenerTokenAuthId(authId);
       Authentication auth = authorizationToken.getAuthId();
       if(authorizationToken.getExpiration_time().isBefore(LocalDateTime.now())){
         FactusTokenResponse factusTokenResponse = webClientService.authenticationRefresh(auth,authorizationToken);
         authorizationToken = authenticationMapper.factusResponseToAuthorizationTokenUpdate(factusTokenResponse,authorizationToken,auth);
       }
       Integer idventa = rangoEnumeracionService.rangoEnumeracionVenta().intValue();
       data.setNumbering_range_id(idventa);
       FacturaResponse responseFactus = webClientService.enviarFacturaAFactus(data,authorizationToken.getAccess_token());
       FacturaDto facturaDto = facturaMapper.responseFactusToDto(responseFactus);
       return ResponseEntity.ok(facturaDto);
    } catch (FacturaException e){
      return manejarFacturaException(e, data);
    }catch(Exception e){
      logErrorInesperado(e);
      FacturaDto facturaDto = facturaMapper.exceptionFactura500Save(e, data);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(facturaDto);
    }
  }

  private void logErrorInesperado(Exception e) {
    logger.error("💥 Error inesperado: {}", e.getMessage());
  }

  private ResponseEntity<FacturaDto> manejarFacturaException(FacturaException ex, FacturaRequest facturaRequest){
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
}
