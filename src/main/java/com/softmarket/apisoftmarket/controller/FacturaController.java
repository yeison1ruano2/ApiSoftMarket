package com.softmarket.apisoftmarket.controller;

import com.softmarket.apisoftmarket.dto.FacturaDto;
import com.softmarket.apisoftmarket.dto.FacturaRequest;
import com.softmarket.apisoftmarket.services.FacturaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/factura")
public class FacturaController {

  private final FacturaService facturaService;

  private static final Logger logger = LoggerFactory.getLogger(FacturaController.class);

  public FacturaController(FacturaService facturaService) {
    this.facturaService = facturaService;
  }

  @PostMapping("/create")
  public ResponseEntity<FacturaDto> crearFactura(@RequestBody FacturaRequest facturaRequest){
    logger.info("Factura",facturaRequest);
    return facturaService.crearfactura(facturaRequest);
  }
}
