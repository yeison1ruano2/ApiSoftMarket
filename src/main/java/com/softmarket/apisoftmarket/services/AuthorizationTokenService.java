package com.softmarket.apisoftmarket.services;

import com.softmarket.apisoftmarket.dto.FactusTokenResponse;
import com.softmarket.apisoftmarket.entity.Authentication;
import com.softmarket.apisoftmarket.entity.AuthorizationToken;

public interface AuthorizationTokenService {

  boolean isTokenExpired(AuthorizationToken token);

  void actualizarToken(Long id);

  void createTokenAuth(Authentication authentication, FactusTokenResponse factusTokenResponse);

  AuthorizationToken obtenerTokenAuthId(String authId);
}
