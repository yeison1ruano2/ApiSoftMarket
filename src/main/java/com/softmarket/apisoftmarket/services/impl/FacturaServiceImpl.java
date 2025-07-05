package com.softmarket.apisoftmarket.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softmarket.apisoftmarket.dto.*;
import com.softmarket.apisoftmarket.entity.Authentication;
import com.softmarket.apisoftmarket.entity.AuthorizationToken;
import com.softmarket.apisoftmarket.entity.Factura;
import com.softmarket.apisoftmarket.exception.Factura409Exception;
import com.softmarket.apisoftmarket.exception.Factura422Exception;
import com.softmarket.apisoftmarket.mapper.AuthenticationMapper;
import com.softmarket.apisoftmarket.repository.FacturaRepository;
import com.softmarket.apisoftmarket.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.softmarket.apisoftmarket.mapper.FacturaMapper;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

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
  private final GoogleDriveService googleDriveService;

  private static final Logger logger = LoggerFactory.getLogger(FacturaServiceImpl.class);

  public FacturaServiceImpl(AuthenticationService authenticationService, AuthorizationTokenService authorizationTokenService, FacturaMapper facturaMapper,
                            ObjectMapper objectMapper, WebClientService webClientService, RangoEnumeracionService rangoEnumeracionService, AuthenticationMapper authenticationMapper, FacturaRepository facturaRepository, GoogleDriveService googleDriveService) {
    this.authenticationService = authenticationService;
    this.authorizationTokenService = authorizationTokenService;
    this.facturaMapper = facturaMapper;
    this.objectMapper = objectMapper;
    this.webClientService = webClientService;
    this.authenticationMapper = authenticationMapper;
    this.rangoEnumeracionService = rangoEnumeracionService;
    this.facturaRepository = facturaRepository;
    this.googleDriveService = googleDriveService;
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
    }catch (Factura422Exception e) {
      return manejarFactura422Exception(e, facturaRequest);
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
       ZoneId colombiaZone = ZoneId.of("America/Bogota");
       LocalDateTime nowColombia = LocalDateTime.now(Clock.system(colombiaZone));
       if(nowColombia.isAfter(authorizationToken.getExpiration_time())){
         FactusTokenResponse factusTokenResponse = webClientService.authenticationRefresh(auth,authorizationToken);
         authorizationToken = authenticationMapper.factusResponseToAuthorizationTokenUpdate(factusTokenResponse,authorizationToken,auth);
       }
       Integer idventa = rangoEnumeracionService.rangoEnumeracionVenta().intValue();
       data.setNumbering_range_id(idventa);
       FacturaResponse responseFactus = webClientService.enviarFacturaAFactus(data,authorizationToken.getAccess_token());
       FacturaDto facturaDto = facturaMapper.responseFactusToDto(responseFactus);
       return ResponseEntity.ok(facturaDto);
    } catch (Factura422Exception e){
      return manejarFactura422Exception(e, data);
    }catch (Factura409Exception e){
      return manejarFactura409Exception(e,data);
    }catch(Exception e){
      logErrorInesperado(e);
      FacturaDto facturaDto = facturaMapper.exceptionFactura500Save(e, data);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(facturaDto);
    }
  }

  @Override
  public ResponseEntity<?> crearFacturaV3(String authId, FacturaRequest data) {
    try{
      AuthorizationToken authorizationToken = authorizationTokenService.obtenerTokenAuthId(authId);
      Authentication auth = authorizationToken.getAuthId();
      ZoneId colombiaZone = ZoneId.of("America/Bogota");
      LocalDateTime nowColombia = LocalDateTime.now(Clock.system(colombiaZone));
      if(nowColombia.isAfter(authorizationToken.getExpiration_time())){
        FactusTokenResponse factusTokenResponse = webClientService.authenticationCreate(auth);
        authorizationToken = authenticationMapper.factusResponseToAuthorizationTokenUpdate(factusTokenResponse,authorizationToken,auth);
      }
      Integer idventa = rangoEnumeracionService.buscarCrearRangoEnumeracion(authorizationToken.getAccess_token()).intValue();
      data.setNumbering_range_id(idventa);
      FacturaResponse responseFactus = webClientService.enviarFacturaAFactus(data,authorizationToken.getAccess_token());
      FacturaPdfFactusResponse facturaPdfFactusResponse = webClientService.descargarPdfFactus(responseFactus.getData().getBill().getNumber(),authorizationToken.getAccess_token());
      String folderIdDrive = "1Xu629qE8FT5lrGU1ibuvToiiHxm7W-p1";
      googleDriveService.guardarPdfBase64EnDrive(facturaPdfFactusResponse.getData().getPdf_base_64_encoded(),responseFactus.getData().getBill().getNumber(),folderIdDrive);
      FacturaDto facturaDto = facturaMapper.responseFactusToDtoV3(responseFactus,facturaPdfFactusResponse);
      return ResponseEntity.ok(facturaDto);
    } catch (Factura422Exception e){
      return manejarFactura422Exception(e, data);
    }catch (Factura409Exception e){
      return manejarFactura409Exception(e,data);
    }
    catch(Exception e){
      logErrorInesperado(e);
      FacturaDto facturaDto = facturaMapper.exceptionFactura500Save(e, data);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(facturaDto);
    }
  }

  private ResponseEntity<?> manejarFactura409Exception(Factura409Exception e, FacturaRequest data) {
    String message = extraerMensajeDeError(e.getMessage());
    FacturaDto facturaDto = facturaMapper.exceptionFacturaSave(e,data,message);
    return ResponseEntity.status(e.getStatus()).body(facturaDto);
  }

  private void logErrorInesperado(Exception e) {
    logger.error("💥 Error inesperado: {}", e.getMessage());
  }

  private ResponseEntity<FacturaDto> manejarFactura422Exception(Factura422Exception ex, FacturaRequest facturaRequest){
    String message = ex.getMessage();
    FacturaDto facturaDto = facturaMapper.exceptionFacturaSave(ex,facturaRequest,message);
    return ResponseEntity.status(ex.getStatus()).body(facturaDto);
  }

  private String extraerMensajeDeError(String message) {
    try {
      JsonNode errorJson = objectMapper.readTree(message);
      return errorJson.has("message")? errorJson.get("message").asText() : errorJson.toString();
    }catch(JsonProcessingException e){
      logger.error("❌ Error al parsear el cuerpo de error: {}", e.getMessage());
      return "Error al parsear cuerpo de error: " + e.getMessage();
    }
  }
}
