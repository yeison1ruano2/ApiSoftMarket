package com.softmarket.apisoftmarket.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softmarket.apisoftmarket.dto.FacturaDto;
import com.softmarket.apisoftmarket.dto.FacturaRequest;
import com.softmarket.apisoftmarket.dto.FacturaResponse;
import com.softmarket.apisoftmarket.entity.Factura;
import com.softmarket.apisoftmarket.exception.FacturaException;
import com.softmarket.apisoftmarket.repository.FacturaRepository;
import com.softmarket.apisoftmarket.services.AuthenticationService;
import com.softmarket.apisoftmarket.services.FacturaService;
import com.softmarket.apisoftmarket.services.RangoEnumeracionService;
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
  private final FacturaRepository facturaRepository;
  private final RangoEnumeracionService rangoEnumeracionService;

  private static final Logger logger = LoggerFactory.getLogger(FacturaServiceImpl.class);

  public FacturaServiceImpl(AuthenticationService authenticationService, FacturaMapper facturaMapper,
                            ObjectMapper objectMapper, WebClientService webClientService, FacturaRepository facturaRepository, RangoEnumeracionService rangoEnumeracionService) {
    this.authenticationService = authenticationService;
    this.facturaMapper = facturaMapper;
    this.objectMapper = objectMapper;
    this.webClientService = webClientService;
    this.facturaRepository = facturaRepository;
    this.rangoEnumeracionService = rangoEnumeracionService;
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
      logger.error("💥 Error inesperado: {}", e.getMessage());
      FacturaDto facturaDto = facturaMapper.exceptionFactura500Save(e, facturaRequest);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(facturaDto);
    }
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
