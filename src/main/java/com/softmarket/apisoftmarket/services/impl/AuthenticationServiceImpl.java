package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.entity.Authentication;
import com.softmarket.apisoftmarket.dto.FactusTokenResponse;
import com.softmarket.apisoftmarket.entity.AuthorizationToken;
import com.softmarket.apisoftmarket.exception.TokenException;
import com.softmarket.apisoftmarket.exception.UsuarioException;
import com.softmarket.apisoftmarket.mapper.AuthenticationMapper;
import com.softmarket.apisoftmarket.repository.AuthenticationRepository;
import com.softmarket.apisoftmarket.repository.AuthorizationTokenRepository;
import com.softmarket.apisoftmarket.services.AuthenticationService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

  private final AuthenticationRepository authenticationRepository;
  private final AuthorizationTokenRepository authorizationTokenRepository;
  private final WebClientService webClientService;
  private final AuthenticationMapper authenticationMapper;

  public AuthenticationServiceImpl(AuthenticationRepository authenticationRepository,
                                   AuthorizationTokenRepository authorizationTokenRepository,
                                   AuthenticationMapper authenticationMapper,
                                   WebClientService webClientService) {
    this.authenticationRepository = authenticationRepository;
    this.authorizationTokenRepository = authorizationTokenRepository;
    this.authenticationMapper = authenticationMapper;
    this.webClientService = webClientService;
  }

  @Override
  public void  authenticationFactus() {
    Authentication auth = authenticationRepository.findFirstAuthentication()
            .orElseThrow(()-> new UsuarioException("No se encontró autenticación"));
    FactusTokenResponse responseFactusToken = webClientService.authenticationCreate(auth);
    authenticationMapper.factusResponseToAuthorizationTokenCreate(responseFactusToken);
  }

  @Override
  public void refreshTokenFactus() {
    Authentication auth = authenticationRepository.findFirstAuthentication()
            .orElseThrow(()-> new UsuarioException("No se encontró autenticación"));

    AuthorizationToken existingToken = authorizationTokenRepository.findFirstAuthentication()
                    .orElseThrow(()-> new UsuarioException("No se encontro token"));
    auth.setGran_type("refresh_token");
    FactusTokenResponse refreshed = webClientService.authenticationRefresh(auth,existingToken);
    authenticationMapper.factusResponseToAuthorizationTokenRefresh(refreshed,existingToken);
  }

  @Override
  public String obtenerToken() {
    return authorizationTokenRepository.findFirstAuthentication().map(AuthorizationToken::getAccess_token)
            .orElseThrow(()->new TokenException("No se encontró token"));
  }
}
