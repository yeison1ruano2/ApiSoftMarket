package com.softmarket.apisoftmarket.services;

import com.softmarket.apisoftmarket.dto.ClientAuthRequest;
import com.softmarket.apisoftmarket.dto.GenericResponse;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface AuthenticationService {
  String obtenerToken();
  Mono<ResponseEntity<GenericResponse>> crearClienteAuth(ClientAuthRequest clientAuthRequest);
}
