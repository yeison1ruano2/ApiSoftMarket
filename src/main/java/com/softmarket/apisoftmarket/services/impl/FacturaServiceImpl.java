package com.softmarket.apisoftmarket.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softmarket.apisoftmarket.dto.*;
import com.softmarket.apisoftmarket.entity.Authentication;
import com.softmarket.apisoftmarket.entity.AuthorizationToken;
import com.softmarket.apisoftmarket.entity.ExternalApiProperties;
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
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

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
  private final ExternalApiProperties externalApiProperties;

  private static final Logger logger = LoggerFactory.getLogger(FacturaServiceImpl.class);

  public FacturaServiceImpl(AuthenticationService authenticationService, AuthorizationTokenService authorizationTokenService, FacturaMapper facturaMapper,
                            ObjectMapper objectMapper, WebClientService webClientService, RangoEnumeracionService rangoEnumeracionService, AuthenticationMapper authenticationMapper, FacturaRepository facturaRepository, GoogleDriveService googleDriveService, ExternalApiProperties externalApiProperties) {
    this.authenticationService = authenticationService;
    this.authorizationTokenService = authorizationTokenService;
    this.facturaMapper = facturaMapper;
    this.objectMapper = objectMapper;
    this.webClientService = webClientService;
    this.authenticationMapper = authenticationMapper;
    this.rangoEnumeracionService = rangoEnumeracionService;
    this.facturaRepository = facturaRepository;
    this.googleDriveService = googleDriveService;
    this.externalApiProperties = externalApiProperties;
  }

  /*@Override
  public ResponseEntity<FacturaDto> crearfactura(FacturaRequest facturaRequest) throws JsonProcessingException {
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
  }*/

  @Override
  public Mono<ResponseEntity<FacturaDto>> crearFacturaV2(String authId, FacturaRequest data) {
    return Mono.fromCallable(() -> authorizationTokenService.obtenerTokenAuthId(authId))
            .subscribeOn(Schedulers.boundedElastic())
            .flatMap(authorizationToken -> {
              Authentication auth = authorizationToken.getAuthId();
              ZoneId colombiaZone = ZoneId.of("America/Bogota");
              LocalDateTime nowColombia = LocalDateTime.now(Clock.system(colombiaZone));

              Mono<AuthorizationToken> tokenMono;

              if (nowColombia.isAfter(authorizationToken.getExpiration_time())) {
                tokenMono = webClientService.authenticationCreate(auth)
                        .map(refresh -> authenticationMapper.factusResponseToAuthorizationTokenUpdate(
                                refresh, authorizationToken, auth
                        ));
              } else {
                tokenMono = Mono.just(authorizationToken);
              }

              return tokenMono.flatMap(updatedToken ->
                      Mono.fromCallable(rangoEnumeracionService::rangoEnumeracionVenta)
                              .subscribeOn(Schedulers.boundedElastic())
                              .map(idVenta -> {
                                data.setNumbering_range_id(idVenta.intValue());
                                return updatedToken;
                              })
              );
            })
            .flatMap(updatedToken ->
                    webClientService.enviarFacturaAFactus(data, updatedToken.getAccess_token())
            )
            .map(responseFactus -> {
              FacturaDto facturaDto = facturaMapper.responseFactusToDto(responseFactus);
              return ResponseEntity.ok(facturaDto);
            })
            .onErrorResume(Factura422Exception.class, e -> Mono.just(manejarFactura422Exception(e, data)))
            .onErrorResume(Factura409Exception.class, e -> Mono.just(manejarFactura409Exception(e, data)))
            .onErrorResume(Exception.class,e -> {
              logErrorInesperado(e);
              FacturaDto facturaDto = facturaMapper.exceptionFactura500Save(e, data);
              return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(facturaDto));
            });
  }

  @Override
  public Mono<ResponseEntity<FacturaDto>> crearFacturaV3(String authId, FacturaRequest data) {
    ZoneId colombiaZone = ZoneId.of("America/Bogota");
    LocalDateTime nowColombia = LocalDateTime.now(Clock.system(colombiaZone));

    return Mono.fromCallable(()-> authorizationTokenService.obtenerTokenAuthId(authId))
            .subscribeOn(Schedulers.boundedElastic())
            .flatMap(authorizationToken -> {
              Authentication auth = authorizationToken.getAuthId();
              Mono<AuthorizationToken>tokenMono;
              if(nowColombia.isAfter(authorizationToken.getExpiration_time())){
                tokenMono = webClientService.authenticationCreate(auth)
                        .flatMap(tokenResponse ->
                                Mono.fromCallable(()->
                                        authenticationMapper.factusResponseToAuthorizationTokenUpdate(
                                                tokenResponse,authorizationToken,auth
                                        ))
                                        .subscribeOn(Schedulers.boundedElastic()));
              }else{
                tokenMono = Mono.just(authorizationToken);
              }
              return tokenMono.flatMap(finalToken ->{
                Mono<AuthorizationToken> rangoMono;
                if(finalToken.getRangoEnumeracionVenta()==null){
                  rangoMono = Mono.fromCallable(()->
                          authorizationTokenService.obtenerRangoEnumeracion(
                                  finalToken.getAccess_token(),finalToken
                          )).subscribeOn(Schedulers.boundedElastic());
                }else{
                  rangoMono = Mono.just(finalToken);
                }
                return rangoMono.flatMap(tokenConRango ->{
                  data.setNumbering_range_id(Integer.parseInt(tokenConRango.getRangoEnumeracionVenta()));
                  return webClientService.enviarFacturaAFactus(data,tokenConRango.getAccess_token())
                          .flatMap(responseFactus -> {
                            String qrImage = responseFactus.getData().getBill().getQr_image();
                            String number = responseFactus.getData().getBill().getNumber();
                            String folderId = externalApiProperties.getFolderIdDrive();
                            return googleDriveService.guardarImageBase64EnDrive(qrImage,number,folderId)
                                    .thenReturn(facturaMapper.responseFactusToDtoV3(responseFactus));
                          });
                });
              });
            })
            .map(ResponseEntity::ok)
            .onErrorResume(Factura422Exception.class,e ->
                    Mono.just(manejarFactura422Exception(e,data)))
            .onErrorResume(Factura409Exception.class,e ->
                    Mono.just(manejarFactura409Exception(e,data)))
            .onErrorResume(Exception.class,e ->{
              logErrorInesperado(e);
              FacturaDto facturaDto = facturaMapper.exceptionFactura500Save(e,data);
              return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(facturaDto));
            });
  }

  private ResponseEntity<FacturaDto> manejarFactura409Exception(Factura409Exception e, FacturaRequest data) {
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
