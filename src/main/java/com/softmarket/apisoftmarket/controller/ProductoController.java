package com.softmarket.apisoftmarket.controller;

import com.softmarket.apisoftmarket.dto.GenericResponse;
import com.softmarket.apisoftmarket.dto.ProductoRequest;
import com.softmarket.apisoftmarket.dto.ProductoResponse;
import com.softmarket.apisoftmarket.services.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
  private final ProductoService productoService;

  public ProductoController(ProductoService productoService) {
    this.productoService = productoService;
  }

  @PostMapping()
  public ResponseEntity<ProductoResponse> crearProducto(@RequestBody ProductoRequest productoRequest){
    return productoService.crearProducto(productoRequest);
  }


  @GetMapping()
  public ResponseEntity<List<ProductoResponse>> listarProductos(){
    return productoService.listarTodos();
  }

  /*@GetMapping("/{id}")
  public ResponseEntity<ProductoResponse> obtenerProducto(@PathVariable Long id){
    return productoService.obtenerPorId(id);
  }*/

  @GetMapping("/nombre")
  public ResponseEntity<List<ProductoResponse>> obtenerProductoNombre(@RequestParam("nombre") String nombre){
    return productoService.obtenerProductoNombre(nombre);
  }

  @GetMapping("/barras")
  public ResponseEntity<List<ProductoResponse>> obtenerProductoBarras(@RequestParam("codigoBarras") String codigoBarras){
    return productoService.obtenerProductoBarras(codigoBarras);
  }

  @PutMapping()
  public ResponseEntity<GenericResponse> actualizarProducto(@RequestParam("codigoBarras") String codigoBarras, @RequestBody ProductoRequest productoRequest){
    return productoService.actualizarProducto(codigoBarras,productoRequest);
  }
}
