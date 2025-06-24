package com.softmarket.apisoftmarket.controller;

import com.softmarket.apisoftmarket.dto.UserResponse;
import com.softmarket.apisoftmarket.dto.UsuarioRequest;
import com.softmarket.apisoftmarket.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authUsuario")
public class InicioSesionController {

  private final UsuarioService usuarioService;

  public InicioSesionController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  @PostMapping("/autenticacion")
  public ResponseEntity<UserResponse> autenticacionUser(@RequestBody UsuarioRequest userRequest){
    return usuarioService.autenticacion(userRequest);
  }

  @PostMapping("/registrar")
  public ResponseEntity<UserResponse> registrarUser(@RequestBody UsuarioRequest userRequest){
    return usuarioService.registrar(userRequest);
  }
}
