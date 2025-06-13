package com.softmarket.apisoftmarket.services;

import com.softmarket.apisoftmarket.dto.GenericResponse;
import com.softmarket.apisoftmarket.dto.ProductoRequest;
import com.softmarket.apisoftmarket.dto.ProductoResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductoService {
  ResponseEntity<ProductoResponse> crearProducto(ProductoRequest productoRequest);

  ResponseEntity<List<ProductoResponse>> listarTodos();

  ResponseEntity<ProductoResponse> obtenerPorId(Long id);

  ResponseEntity<List<ProductoResponse>> obtenerProductoNombre(String nombre);

  ResponseEntity<GenericResponse> actualizarProducto(String codigoBarras, ProductoRequest productoRequest);

  ResponseEntity<List<ProductoResponse>> obtenerProductoBarras(String codigoBarras);
}
