package com.softmarket.ApiSoftMarket.services;

import com.softmarket.ApiSoftMarket.entity.AuthorizationToken;

public interface AuthorizationTokenService {

  boolean isTokenExpired(AuthorizationToken token);
}
