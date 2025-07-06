package com.softmarket.apisoftmarket.services;

import com.softmarket.apisoftmarket.dto.GenericResponse;
import com.softmarket.apisoftmarket.dto.MarcaRequest;
import com.softmarket.apisoftmarket.dto.MarcaResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MarcaService {
  ResponseEntity<GenericResponse> crearMarca(MarcaRequest marcaRequet);
  ResponseEntity<GenericResponse> actualizarMarca(String id, MarcaRequest marcaRequest);
  ResponseEntity<List<MarcaResponse>> obtenerMarcas();
  List<String> obtenerMarcasList();
  ResponseEntity<List<MarcaResponse>> obtenerMarcaNombreList(String nombre);
}
