package com.softmarket.apisoftmarket.mapper;

import com.softmarket.apisoftmarket.entity.AuthorizationToken;
import com.softmarket.apisoftmarket.entity.FactusTokenResponse;
import com.softmarket.apisoftmarket.repository.AuthorizationTokenRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationMapper {
  private final AuthorizationTokenRepository authorizationTokenRepository;

  public AuthenticationMapper(AuthorizationTokenRepository authorizationTokenRepository) {
    this.authorizationTokenRepository = authorizationTokenRepository;
  }
  public FactusTokenResponse tokenToFactusResponse(AuthorizationToken token) {
    return new FactusTokenResponse(
            token.getAccess_token(),
            token.getExpires_in(),
            token.getRefresh_token(),
            token.getToken_type()
    );
  }

  public FactusTokenResponse factusResponseToAuthorizationTokenCreate(FactusTokenResponse factusTokenResponse) {
    AuthorizationToken authorizationToken = new AuthorizationToken(
            factusTokenResponse.getAccess_token(),
            factusTokenResponse.getExpires_in(),
            factusTokenResponse.getRefresh_token(),
            factusTokenResponse.getToken_type()
    );
    authorizationTokenRepository.save(authorizationToken);
    return factusTokenResponse;
  }

  public FactusTokenResponse factusResponseToAuthorizationTokenRefresh(FactusTokenResponse factusTokenResponse, AuthorizationToken token) {
    AuthorizationToken authorizationToken = new AuthorizationToken(
            token.getId(),
            factusTokenResponse.getAccess_token(),
            factusTokenResponse.getExpires_in(),
            factusTokenResponse.getRefresh_token(),
            factusTokenResponse.getToken_type()
    );
    authorizationTokenRepository.save(authorizationToken);
    return factusTokenResponse;
  }
}

