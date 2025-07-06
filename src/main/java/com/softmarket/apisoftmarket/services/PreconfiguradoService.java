package com.softmarket.apisoftmarket.services;

import com.softmarket.apisoftmarket.dto.PreconfiguradoResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PreconfiguradoService {
  ResponseEntity<PreconfiguradoResponse> obtenerPreconfigurado();
}
