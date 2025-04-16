package com.softmarket.ApiSoftMarket.controller;

import com.softmarket.ApiSoftMarket.dto.FacturaDto;
import com.softmarket.ApiSoftMarket.dto.FacturaRequest;
import com.softmarket.ApiSoftMarket.entity.FactusTokenResponse;
import com.softmarket.ApiSoftMarket.services.AuthenticationService;
import com.softmarket.ApiSoftMarket.services.FacturaService;
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
  public ResponseEntity<FacturaDto> crearFactura(@RequestBody FacturaRequest facturaRequest){
    return facturaService.crearfactura(facturaRequest);
  }
}
