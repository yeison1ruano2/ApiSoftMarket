package com.softmarket.apisoftmarket.services;

import com.softmarket.apisoftmarket.dto.UserRequest;
import com.softmarket.apisoftmarket.dto.UserResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
  ResponseEntity<UserResponse> autenticacion(UserRequest userRequest);
  ResponseEntity<UserResponse> registrar(UserRequest userRequest);
}
