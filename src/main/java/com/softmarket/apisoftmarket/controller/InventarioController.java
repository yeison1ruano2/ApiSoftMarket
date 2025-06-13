package com.softmarket.apisoftmarket.controller;

import com.softmarket.apisoftmarket.dto.GenericResponse;
import com.softmarket.apisoftmarket.dto.InventarioRequestStock;
import com.softmarket.apisoftmarket.dto.InventarioResponse;
import com.softmarket.apisoftmarket.services.InventarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

  private final InventarioService inventarioService;

  public InventarioController(InventarioService inventarioService) {
    this.inventarioService = inventarioService;
  }

  @PostMapping("/stock")
  public ResponseEntity<GenericResponse> agregarRetirarStock( @RequestBody InventarioRequestStock inventarioRequestStock){
    if(inventarioRequestStock.getTipoMovimiento().equalsIgnoreCase("SALIDA")){
      return inventarioService.retirarStock(inventarioRequestStock.getCodigoBarras(), inventarioRequestStock.getCantidad());
    }else{
      return inventarioService.ingresarStock(inventarioRequestStock.getCodigoBarras(), inventarioRequestStock.getCantidad());
    }
  }

  @GetMapping("/{codigoBarras}")
  public ResponseEntity<InventarioResponse> verInventario(@PathVariable String codigoBarras) {
     return inventarioService.obtenerInventario(codigoBarras);
  }

}
