package com.softmarket.apisoftmarket.controller;

import com.softmarket.apisoftmarket.dto.PreconfiguradoResponse;
import com.softmarket.apisoftmarket.services.PreconfiguradoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/preconfigurado")
public class PreconfiguradoController {

  private final PreconfiguradoService preconfiguradoService;

  public PreconfiguradoController(PreconfiguradoService preconfiguradoService) {
    this.preconfiguradoService = preconfiguradoService;
  }

  @GetMapping()
  public ResponseEntity<PreconfiguradoResponse> obtenerPreconfigurado(){
    return preconfiguradoService.obtenerPreconfigurado();
  }
}
