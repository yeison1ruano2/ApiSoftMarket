package com.softmarket.apisoftmarket.services;

import com.softmarket.apisoftmarket.dto.PreconfiguradoResponse;
import org.springframework.http.ResponseEntity;

public interface PreconfiguradoService {
  ResponseEntity<PreconfiguradoResponse> obtenerPreconfigurado();
}
