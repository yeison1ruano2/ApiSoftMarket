package com.softmarket.ApiSoftMarket.services;

import com.softmarket.ApiSoftMarket.dto.FacturaDto;
import com.softmarket.ApiSoftMarket.dto.FacturaRequest;
import org.springframework.http.ResponseEntity;

public interface FacturaService {
  ResponseEntity<FacturaDto> crearfactura(FacturaRequest facturaRequest);
}
