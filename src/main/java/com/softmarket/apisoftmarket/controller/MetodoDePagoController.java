package com.softmarket.apisoftmarket.controller;

import com.softmarket.apisoftmarket.dto.MetodoDePagoResponse;
import com.softmarket.apisoftmarket.services.MetodoDePagoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/metodoPago")
public class MetodoDePagoController {

  private final MetodoDePagoService metodoDePagoService;

  public MetodoDePagoController(MetodoDePagoService metodoDePagoService) {
    this.metodoDePagoService = metodoDePagoService;
  }

  @GetMapping()
  public ResponseEntity<List<MetodoDePagoResponse>> listarMetodosDePago(){
    return metodoDePagoService.listarMetodosDePago();
  }

  @GetMapping("{termino}")
  public ResponseEntity<MetodoDePagoResponse> listarMetodoDePagoTermino(@PathVariable String termino){
    return metodoDePagoService.listarMetodoDePagoTermino(termino);
  }
}
