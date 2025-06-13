package com.softmarket.apisoftmarket.services;

import com.softmarket.apisoftmarket.dto.GenericResponse;
import com.softmarket.apisoftmarket.dto.MarcaRequest;
import com.softmarket.apisoftmarket.dto.MarcaResponse;
import com.softmarket.apisoftmarket.entity.Marca;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MarcaService {
  ResponseEntity<GenericResponse> crearMarca(MarcaRequest marcaRequet);

  ResponseEntity<GenericResponse> actualizarMarca(String id, MarcaRequest marcaRequest);

  ResponseEntity<List<MarcaResponse>> obtenerMarcas();

  ResponseEntity<List<MarcaResponse>> obtenerMarcaNombreList(String nombre);

  Marca obtenerMarcaNombre(String nombre);
}
