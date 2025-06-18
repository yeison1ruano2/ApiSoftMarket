package com.softmarket.apisoftmarket.controller;

import com.softmarket.apisoftmarket.dto.GenericResponse;
import com.softmarket.apisoftmarket.dto.MarcaRequest;
import com.softmarket.apisoftmarket.dto.MarcaResponse;
import com.softmarket.apisoftmarket.services.MarcaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marca")
public class MarcaController {

  private final MarcaService marcaService;

  public MarcaController(MarcaService marcaService) {
    this.marcaService = marcaService;
  }

  @PostMapping()
  public ResponseEntity<GenericResponse> crearMarca(@RequestBody MarcaRequest marcaRequest){
    return marcaService.crearMarca(marcaRequest);
  }

  @PutMapping("/{id}")
  public ResponseEntity<GenericResponse> actualizarMarca(@PathVariable String id,@RequestBody MarcaRequest marcaRequest){
    return marcaService.actualizarMarca(id,marcaRequest);
  }

  @GetMapping()
  public ResponseEntity<List<MarcaResponse>> obtenerMarcas(){
    return marcaService.obtenerMarcas();
  }

  @GetMapping("/nombre")
  public ResponseEntity<List<MarcaResponse>> obtenerMarcaNombre(@RequestParam("nombre") String nombre){
    return marcaService.obtenerMarcaNombreList(nombre);
  }

}
