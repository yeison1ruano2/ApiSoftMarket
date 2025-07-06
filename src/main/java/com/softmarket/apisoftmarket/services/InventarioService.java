package com.softmarket.apisoftmarket.services;

import com.softmarket.apisoftmarket.dto.GenericResponse;
import com.softmarket.apisoftmarket.dto.InventarioResponse;
import com.softmarket.apisoftmarket.dto.ProductoResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InventarioService {
  ResponseEntity<GenericResponse> ingresarStock(String codigoBarras, Integer cantidad);
  ResponseEntity<GenericResponse> retirarStock(String codigoBarras, Integer cantidad);
  ResponseEntity<InventarioResponse> obtenerInventario(String codigoBarras);
  void crearInventario(Long productoId,Integer stockMinimo);
  Integer obtenerStock(String codigoBarras);
  List<ProductoResponse> obtenerProductoList();
}
