package com.softmarket.apisoftmarket.services;

import com.softmarket.apisoftmarket.dto.GenericResponse;
import com.softmarket.apisoftmarket.dto.InventarioResponse;
import org.springframework.http.ResponseEntity;

public interface InventarioService {
  ResponseEntity<GenericResponse> ingresarStock(String codigoBarras, Integer cantidad);
  ResponseEntity<GenericResponse> retirarStock(String codigoBarras, int cantidad);
  ResponseEntity<InventarioResponse> obtenerInventario(String codigoBarras);
  InventarioResponse crearStock(Long productoId,Integer stockMinimo);

}
