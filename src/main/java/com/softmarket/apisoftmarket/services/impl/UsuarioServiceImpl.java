package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.dto.UserResponse;
import com.softmarket.apisoftmarket.dto.UsuarioRequest;
import com.softmarket.apisoftmarket.dto.UsuarioResponse;
import com.softmarket.apisoftmarket.entity.Token;
import com.softmarket.apisoftmarket.entity.Usuario;
import com.softmarket.apisoftmarket.exception.UsuarioException;
import com.softmarket.apisoftmarket.mapper.TokenMapper;
import com.softmarket.apisoftmarket.mapper.UsuarioMapper;
import com.softmarket.apisoftmarket.repository.UsuarioRepository;
import com.softmarket.apisoftmarket.services.TokenService;
import com.softmarket.apisoftmarket.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

  private final UsuarioRepository usuarioRepository;
  private final PasswordEncoder passwordEncoder;
  private final TokenService tokenService;
  private final JwtService jwtService;
  private final TokenMapper tokenMapper;
  private final UsuarioMapper usuarioMapper;

  public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, TokenService tokenService, JwtService jwtService, TokenMapper tokenMapper, UsuarioMapper usuarioMapper) {
    this.usuarioRepository = usuarioRepository;
    this.passwordEncoder = passwordEncoder;
    this.tokenService = tokenService;
    this.jwtService = jwtService;
    this.tokenMapper = tokenMapper;
    this.usuarioMapper = usuarioMapper;
  }

  @Override
  public UsuarioResponse obtenerUsuarioId(Long id) {
    Usuario usuario =  usuarioRepository.findById(id).get();
    return new UsuarioResponse(
          usuario.getNombre(),
          usuario.getApellido(),
          usuario.getIdentificacion()
    );
  }

  @Override
  public UsuarioResponse obtenerUsuarioResponseId(Long id) {
    Usuario usuario =  usuarioRepository.findById(id).orElseThrow(()->new UsuarioException("Usuario no encontrado"));
    return new UsuarioResponse(
            usuario.getNombre(),
            usuario.getApellido(),
            usuario.getIdentificacion()
    );
  }

  @Override
  public ResponseEntity<UserResponse> autenticacion(UsuarioRequest userRequest) {
    Usuario usuario = usuarioRepository.findByUsername(userRequest.getUsername()).orElseThrow(()-> new UsuarioException("Usuario no encontrado"));
    if(!passwordEncoder.matches(userRequest.getPassword(),usuario.getPassword())){
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserResponse(HttpStatus.UNAUTHORIZED.value(),"","", "Usuario o credenciales no coinciden"));
    }
    String finalToken = obtenerTokenOActualizarExpirado(usuario);
    return ResponseEntity.status(HttpStatus.OK).body(new UserResponse(HttpStatus.OK.value(), finalToken,usuario.getNombre(),"Usuario autenticado con éxito"));
  }

  private String obtenerTokenOActualizarExpirado(Usuario usuario) {
    return tokenService.obtenerUsuario(usuario)
            .map(token -> {
              if(jwtService.isValid(token.getToken(),usuario)){
                return token.getToken();
              }else{
                String nuevoToken = jwtService.generateToken(usuario);
                token.setToken(nuevoToken);
                tokenService.guardarToken(token);
                return nuevoToken;
              }
            }).orElseGet(()->{
              String tokenGenerado = jwtService.generateToken(usuario);
              Token token = tokenMapper.tokenCreate(tokenGenerado,usuario);
              tokenService.guardarToken(token);
              return token.getToken();
            });
  }

  @Override
  public ResponseEntity<UserResponse> registrar(UsuarioRequest userRequest) {
    return usuarioRepository.findByUsername(userRequest.getUsername())
            .map(usuario-> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserResponse(HttpStatus.BAD_REQUEST.value(),"","","El username ya existe")))
            .orElseGet(()->{
              Usuario user = usuarioMapper.requestToEntityCreate(userRequest);
              usuarioRepository.save(user);
              return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse(HttpStatus.CREATED.value(),"","", "Usuario creado con éxito"));
            });
  }

  @Override
  public Optional<Usuario> obtenerUsuarioUsername(String username) {
    return usuarioRepository.findByUsername(username);
  }


}
