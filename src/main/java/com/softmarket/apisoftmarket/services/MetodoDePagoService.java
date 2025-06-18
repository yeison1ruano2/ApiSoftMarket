package com.softmarket.apisoftmarket.services;

import com.softmarket.apisoftmarket.dto.MetodoDePagoResponse;
import com.softmarket.apisoftmarket.entity.MetodoDePago;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MetodoDePagoService {
  ResponseEntity<List<MetodoDePagoResponse>> listarMetodosDePago();

  ResponseEntity<MetodoDePagoResponse> listarMetodoDePagoTermino(String termino);

  MetodoDePago obtenerMetodoPagoTermino(String termino);
}
