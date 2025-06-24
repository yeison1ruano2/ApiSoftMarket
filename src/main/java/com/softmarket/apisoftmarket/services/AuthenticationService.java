package com.softmarket.apisoftmarket.services;

import com.softmarket.apisoftmarket.dto.ClientAuthRequest;
import com.softmarket.apisoftmarket.dto.GenericResponse;
import com.softmarket.apisoftmarket.entity.Authentication;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
  String obtenerToken();
  Authentication obtenerAuth(Long idAuth);
  ResponseEntity<GenericResponse> crearClienteAuth(ClientAuthRequest clientAuthRequest);
}
