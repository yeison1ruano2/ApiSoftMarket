package com.softmarket.apisoftmarket.controller;

import com.softmarket.apisoftmarket.dto.ClientAuthRequest;
import com.softmarket.apisoftmarket.dto.GenericResponse;
import com.softmarket.apisoftmarket.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/autenticacionAuth")
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  public AuthenticationController(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  @PostMapping()
  public Mono<ResponseEntity<GenericResponse>> crearClienteAuth(@RequestBody ClientAuthRequest clientAuthRequest){
    return authenticationService.crearClienteAuth(clientAuthRequest);
  }

}
