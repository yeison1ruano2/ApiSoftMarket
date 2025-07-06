package com.softmarket.apisoftmarket.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.softmarket.apisoftmarket.dto.FacturaDto;
import com.softmarket.apisoftmarket.dto.FacturaRequest;
import org.springframework.http.ResponseEntity;

public interface FacturaService {
  ResponseEntity<FacturaDto> crearfactura(FacturaRequest facturaRequest) throws JsonProcessingException;
  ResponseEntity<FacturaDto> crearFacturaV2(String clientId, FacturaRequest data);
  ResponseEntity<FacturaDto> crearFacturaV3(String authId, FacturaRequest data);
}
