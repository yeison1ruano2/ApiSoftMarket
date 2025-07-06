package com.softmarket.apisoftmarket.services;

import com.softmarket.apisoftmarket.dto.ClientAuthRequest;
import com.softmarket.apisoftmarket.dto.GenericResponse;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
  String obtenerToken();
  ResponseEntity<GenericResponse> crearClienteAuth(ClientAuthRequest clientAuthRequest);
}
