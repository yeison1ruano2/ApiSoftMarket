package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.dto.UserRequest;
import com.softmarket.apisoftmarket.dto.UserResponse;
import com.softmarket.apisoftmarket.entity.Token;
import com.softmarket.apisoftmarket.entity.Usuario;
import com.softmarket.apisoftmarket.exception.UsuarioException;
import com.softmarket.apisoftmarket.mapper.AuthMapper;
import com.softmarket.apisoftmarket.mapper.TokenMapper;
import com.softmarket.apisoftmarket.repository.TokenRepository;
import com.softmarket.apisoftmarket.repository.UserRepository;
import com.softmarket.apisoftmarket.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthMapper authMapper;
  private final TokenMapper tokenMapper;
  private final TokenRepository tokenRepository;

  public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthMapper authMapper, TokenMapper tokenMapper, TokenRepository tokenRepository) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
    this.authMapper = authMapper;
    this.tokenMapper = tokenMapper;
    this.tokenRepository = tokenRepository;
  }

  @Override
  public ResponseEntity<UserResponse> autenticacion(UserRequest userRequest) {
    Usuario usuario = userRepository.findByUsername(userRequest.getUsername()).orElseThrow(()-> new UsuarioException("Usuario no encontrado"));
    if(!passwordEncoder.matches(userRequest.getPassword(),usuario.getPassword())){
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserResponse(HttpStatus.UNAUTHORIZED.value(),"","", "Usuario o credenciales no coinciden"));
    }
    String finalToken = obtenerTokenOActualizarExpirado(usuario);
    return ResponseEntity.status(HttpStatus.OK).body(new UserResponse(HttpStatus.OK.value(), finalToken,usuario.getNombre(),"Usuario autenticado con éxito"));
  }

  private String obtenerTokenOActualizarExpirado(Usuario usuario) {
    return tokenRepository.findByUsuario(usuario)
            .map(token ->{
              if(jwtService.isValid(token.getToken(),usuario)){
                return token.getToken();
              }else{
                String nuevoToken = jwtService.generateToken(usuario);
                token.setToken(nuevoToken);
                tokenRepository.save(token);
                return nuevoToken;
              }
            }).orElseGet(()->{
              String nuevoToken = jwtService.generateToken(usuario);
              Token token = tokenMapper.tokenCreate(nuevoToken,usuario);
              tokenRepository.save(token);
              return nuevoToken;
            });
  }

  @Override
  public ResponseEntity<UserResponse> registrar(UserRequest userRequest) {
    return userRepository.findByUsername(userRequest.getUsername())
            .map(usuario-> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserResponse(HttpStatus.BAD_REQUEST.value(),"","","El username ya existe")))
            .orElseGet(()->{
              Usuario user = authMapper.requestToEntityCreate(userRequest);
              userRepository.save(user);
              return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse(HttpStatus.CREATED.value(),"","", "Usuario creado con éxito"));
            });
  }
}
