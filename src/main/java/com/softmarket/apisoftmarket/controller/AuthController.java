package com.softmarket.apisoftmarket.controller;

import com.softmarket.apisoftmarket.dto.UserRequest;
import com.softmarket.apisoftmarket.dto.UserResponse;
import com.softmarket.apisoftmarket.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/autenticacion")
  public ResponseEntity<UserResponse> autenticacionUser(@RequestBody UserRequest userRequest){
    return authService.autenticacion(userRequest);
  }

  @PostMapping("/registrar")
  public ResponseEntity<UserResponse> registrarUser(@RequestBody UserRequest userRequest){
    return authService.registrar(userRequest);
  }
}
