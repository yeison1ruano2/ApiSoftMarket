package com.softmarket.apisoftmarket.services;

import com.softmarket.apisoftmarket.dto.UserResponse;
import com.softmarket.apisoftmarket.dto.UsuarioRequest;
import com.softmarket.apisoftmarket.dto.UsuarioResponse;
import com.softmarket.apisoftmarket.entity.Usuario;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UsuarioService {
  UsuarioResponse obtenerUsuarioId(Long id);
  ResponseEntity<UserResponse> autenticacion(UsuarioRequest usuarioRequest);
  ResponseEntity<UserResponse> registrar(UsuarioRequest usuarioRequest);

  Optional<Usuario> obtenerUsuarioUsername(String username);

  UsuarioResponse obtenerUsuarioResponseId(Long id);
}
