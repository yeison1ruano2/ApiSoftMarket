package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.dto.FactusTokenResponse;
import com.softmarket.apisoftmarket.entity.Authentication;
import com.softmarket.apisoftmarket.entity.AuthorizationToken;
import com.softmarket.apisoftmarket.exception.AuthorizationTokenException;
import com.softmarket.apisoftmarket.mapper.AuthorizationTokenMapper;
import com.softmarket.apisoftmarket.repository.AuthorizationTokenRepository;
import com.softmarket.apisoftmarket.services.AuthorizationTokenService;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationTokenServiceImpl implements AuthorizationTokenService {

  private final AuthorizationTokenMapper authorizationTokenMapper;
  private final AuthorizationTokenRepository authorizationTokenRepository;

  public AuthorizationTokenServiceImpl(AuthorizationTokenMapper authorizationTokenMapper, AuthorizationTokenRepository authorizationTokenRepository) {
    this.authorizationTokenMapper = authorizationTokenMapper;
    this.authorizationTokenRepository = authorizationTokenRepository;
  }

  @Override
  public boolean isTokenExpired(AuthorizationToken token) {
    return false;
  }

  @Override
  public void actualizarToken(Long id) {

  }

  @Override
  public void createTokenAuth(Authentication authentication, FactusTokenResponse factusTokenResponse) {
    AuthorizationToken authorizationTokenCreate = authorizationTokenMapper.createTokenAuth(authentication,factusTokenResponse);
    authorizationTokenRepository.save(authorizationTokenCreate);
  }

  @Override
  public AuthorizationToken obtenerTokenAuthId(String authId) {
    return authorizationTokenRepository.findByAuthIdId(Long.parseLong(authId)).orElseThrow(()->new AuthorizationTokenException("Token autenticación no encontrado"));
  }
}
