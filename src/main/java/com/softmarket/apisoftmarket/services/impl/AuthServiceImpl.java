package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.dto.UserRequest;
import com.softmarket.apisoftmarket.dto.UserResponse;
import com.softmarket.apisoftmarket.entity.Usuario;
import com.softmarket.apisoftmarket.mapper.AuthMapper;
import com.softmarket.apisoftmarket.repository.UserRepository;
import com.softmarket.apisoftmarket.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthMapper authMapper;

  public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthMapper authMapper) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
    this.authMapper = authMapper;
  }

  @Override
  public ResponseEntity<UserResponse> autenticacion(UserRequest userRequest) {
    Optional<Usuario> userOptional = userRepository.findByUsername(userRequest.getUsername());
    if(userOptional.isEmpty()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserResponse(HttpStatus.NOT_FOUND.getReasonPhrase(),"","","Usuario no encontrado"));
    }
    if(!passwordEncoder.matches(userRequest.getPassword(),userOptional.get().getPassword())){
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserResponse(HttpStatus.UNAUTHORIZED.getReasonPhrase(),"","", "Contraseña incorrecta"));
    }
    String token = jwtService.generateToken(userOptional.get());
    return ResponseEntity.status(HttpStatus.OK).body(new UserResponse(HttpStatus.OK.getReasonPhrase(), token,userOptional.get().getNombre(),"Usuario autenticado con éxito"));
  }

  @Override
  public ResponseEntity<UserResponse> registrar(UserRequest userRequest) {
    Usuario user = authMapper.requestToEntity(userRequest);
    userRepository.save(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse(HttpStatus.CREATED.getReasonPhrase(),"","", "Usuario creado con éxito"));
  }
}
