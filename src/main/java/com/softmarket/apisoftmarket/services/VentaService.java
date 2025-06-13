package com.softmarket.apisoftmarket.services;

import com.softmarket.apisoftmarket.dto.GenericResponse;
import com.softmarket.apisoftmarket.dto.VentaRequest;
import com.softmarket.apisoftmarket.dto.VentaResponse;
import com.softmarket.apisoftmarket.entity.Venta;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VentaService {
  GenericResponse registrarVenta(VentaRequest ventaRequest);

  ResponseEntity<VentaResponse> listarVentasFactura(String codigoFactura);

  ResponseEntity<List<VentaResponse>> listarVentas();
}
