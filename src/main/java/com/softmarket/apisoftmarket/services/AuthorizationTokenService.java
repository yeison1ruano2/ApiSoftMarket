package com.softmarket.apisoftmarket.services;

import com.softmarket.apisoftmarket.entity.AuthorizationToken;

public interface AuthorizationTokenService {

  boolean isTokenExpired(AuthorizationToken token);
}
