package com.softmarket.apisoftmarket.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.softmarket.apisoftmarket.dto.AuthFacturaRequest;
import com.softmarket.apisoftmarket.dto.FacturaDto;
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
  public ResponseEntity<FacturaDto> crearFactura(@RequestBody FacturaRequest facturaRequest) throws JsonProcessingException {
    return facturaService.crearfactura(facturaRequest);
  }

  @PostMapping("/create/v2")
  public ResponseEntity<FacturaDto> crearFacturaV2(@RequestBody AuthFacturaRequest authFacturaRequest){
    return facturaService.crearFacturaV2(authFacturaRequest.getAuth_id(),authFacturaRequest.getData());
  }

  @PostMapping("/create/v3")
  public ResponseEntity<FacturaDto> crearFacturav3(@RequestBody AuthFacturaRequest authFacturaRequest){
    return facturaService.crearFacturaV3(authFacturaRequest.getAuth_id(),authFacturaRequest.getData());
  }

}
