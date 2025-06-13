package com.softmarket.apisoftmarket.controller;

import com.softmarket.apisoftmarket.dto.CategoriaRequest;
import com.softmarket.apisoftmarket.dto.CategoriaResponse;
import com.softmarket.apisoftmarket.dto.GenericResponse;
import com.softmarket.apisoftmarket.services.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

  private final CategoriaService categoriaService;

  public CategoriaController(CategoriaService categoriaService) {
    this.categoriaService = categoriaService;
  }

  @PostMapping()
  public ResponseEntity<GenericResponse> crearCategoria(@RequestBody CategoriaRequest categoriaRequest){
    return categoriaService.crearCategoria(categoriaRequest);
  }

  @GetMapping("/nombre")
  public ResponseEntity<List<CategoriaResponse>> obtenerCategoriaNombre(@RequestParam("nombre") String nombre){
    return categoriaService.obtenerCategoriaNombreList(nombre);
  }

  @PutMapping("/{id}")
  public ResponseEntity<GenericResponse> actualizarCategoria(@PathVariable Integer id, CategoriaRequest categoriaRequest){
    return categoriaService.actualizarCategoria(id,categoriaRequest);
  }
}
