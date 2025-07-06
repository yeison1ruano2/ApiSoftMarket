package com.softmarket.apisoftmarket.services;

import com.softmarket.apisoftmarket.dto.CategoriaRequest;
import com.softmarket.apisoftmarket.dto.CategoriaResponse;
import com.softmarket.apisoftmarket.dto.GenericResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoriaService {
  ResponseEntity<GenericResponse> crearCategoria(CategoriaRequest categoriaRequest);
  ResponseEntity<GenericResponse> actualizarCategoria(Integer id, CategoriaRequest categoriaRequest);
  ResponseEntity<List<CategoriaResponse>> obtenerCategoriaNombreList(String nombre);
  List<String> obtenerCategoriaList();
}
