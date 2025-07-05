package com.softmarket.apisoftmarket.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.softmarket.apisoftmarket.dto.FacturaRequest;
import org.springframework.http.ResponseEntity;

public interface FacturaService {
  ResponseEntity<?> crearfactura(FacturaRequest facturaRequest) throws JsonProcessingException;

  ResponseEntity<?> crearFacturaV2(String clientId, FacturaRequest data);

  ResponseEntity<?> crearFacturaV3(String authId, FacturaRequest data);
}
