package com.softmarket.apisoftmarket.controller;

import com.softmarket.apisoftmarket.dto.GenericResponse;
import com.softmarket.apisoftmarket.dto.VentaRequest;
import com.softmarket.apisoftmarket.dto.VentaResponse;
import com.softmarket.apisoftmarket.entity.Venta;
import com.softmarket.apisoftmarket.services.VentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

  private final VentaService ventaService;

  public VentaController(VentaService ventaService) {
    this.ventaService = ventaService;
  }

  @PostMapping()
  public ResponseEntity<GenericResponse> registrarVenta(@RequestBody VentaRequest ventaRequest){
    GenericResponse genericResponse = ventaService.registrarVenta(ventaRequest);
    return ResponseEntity.ok(genericResponse);
  }

  @GetMapping("/factura")
  public ResponseEntity<VentaResponse> listarVentasFactura(@RequestParam("codigoFactura") String codigoFactura){
    return ventaService.listarVentasFactura(codigoFactura);
  }

  @GetMapping()
  public ResponseEntity<List<VentaResponse>> listarVentas(){
    return ventaService.listarVentas();
  }
}
