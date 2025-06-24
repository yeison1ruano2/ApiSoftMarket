package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.dto.ClientAuthRequest;
import com.softmarket.apisoftmarket.dto.GenericResponse;
import com.softmarket.apisoftmarket.entity.Authentication;
import com.softmarket.apisoftmarket.dto.FactusTokenResponse;
import com.softmarket.apisoftmarket.entity.AuthorizationToken;
import com.softmarket.apisoftmarket.exception.*;
import com.softmarket.apisoftmarket.mapper.AuthenticationMapper;
import com.softmarket.apisoftmarket.repository.AuthenticationRepository;
import com.softmarket.apisoftmarket.repository.AuthorizationTokenRepository;
import com.softmarket.apisoftmarket.services.AuthenticationService;
import com.softmarket.apisoftmarket.services.AuthorizationTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

  private final AuthenticationRepository authenticationRepository;
  private final AuthorizationTokenRepository authorizationTokenRepository;
  private final WebClientService webClientService;
  private final AuthenticationMapper authenticationMapper;
  private final AuthorizationTokenService authorizationTokenService;

  public AuthenticationServiceImpl(AuthenticationRepository authenticationRepository,
                                   AuthorizationTokenRepository authorizationTokenRepository,
                                   AuthenticationMapper authenticationMapper,
                                   WebClientService webClientService, AuthorizationTokenService authorizationTokenService) {
    this.authenticationRepository = authenticationRepository;
    this.authorizationTokenRepository = authorizationTokenRepository;
    this.authenticationMapper = authenticationMapper;
    this.webClientService = webClientService;
    this.authorizationTokenService = authorizationTokenService;
  }

  @Override
  public String obtenerToken() {
    AuthorizationToken authorizationToken = authorizationTokenRepository.findById(Long.parseLong("30")).orElseThrow(()-> new AuthenticationException("No se encontro Token"));
    return authorizationToken.getAccess_token();
  }

  @Override
  public Authentication obtenerAuth(Long authId) {
    return authenticationRepository.findById(authId)
            .orElseThrow(()->new AuthenticationException("Autenticacion no existe"));
  }

  @Override
  public ResponseEntity<GenericResponse> crearClienteAuth(ClientAuthRequest clientAuthRequest) {
    Authentication authentication = authenticationMapper.crearClientAuth(clientAuthRequest);
    authentication = authenticationRepository.save(authentication);
    FactusTokenResponse factusTokenResponse = webClientService.authenticationCreate(authentication);
    authorizationTokenService.createTokenAuth(authentication,factusTokenResponse);
    return ResponseEntity.ok(new GenericResponse(HttpStatus.OK.value(),"Authenticacion exitosa"));
  }
}
