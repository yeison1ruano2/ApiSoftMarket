package com.softmarket.apisoftmarket.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.softmarket.apisoftmarket.dto.FacturaRequest;
import com.softmarket.apisoftmarket.services.FacturaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/factura")
public class FacturaController {

  private final FacturaService facturaService;

  public FacturaController(FacturaService facturaService) {
    this.facturaService = facturaService;
  }

  @PostMapping("/create")
  public ResponseEntity<?> crearFactura(@RequestBody FacturaRequest facturaRequest) throws JsonProcessingException {
    return facturaService.crearfactura(facturaRequest);
  }
}
