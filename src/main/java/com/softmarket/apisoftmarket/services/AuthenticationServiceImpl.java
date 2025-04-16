package com.softmarket.apisoftmarket.services;

import com.softmarket.apisoftmarket.entity.Authentication;
import com.softmarket.apisoftmarket.entity.AuthorizationToken;
import com.softmarket.apisoftmarket.entity.FactusTokenResponse;
import com.softmarket.apisoftmarket.mapper.AuthenticationMapper;
import com.softmarket.apisoftmarket.repository.AuthenticationRepository;
import com.softmarket.apisoftmarket.repository.AuthorizationTokenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements  AuthenticationService{

  private final AuthenticationRepository authenticationRepository;
  private final AuthorizationTokenRepository authorizationTokenRepository;
  private final AuthenticationMapper authenticationMapper;
  private final WebClientService webClientService;

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
  public FactusTokenResponse  authenticationFactus() {
    Optional<AuthorizationToken> authorizationTokenOptional = authorizationTokenRepository.findFirstAuthentication();
    if(authorizationTokenOptional.isEmpty()){
       return firstAuthentication();
    }
    AuthorizationToken token = authorizationTokenOptional.get();
    if(token.getExpiration_time().isBefore(LocalDateTime.now())){
      return AuthenticationRefreshToken();
    }
    return authenticationMapper.tokenToFactusResponse(token);
  }

  private FactusTokenResponse firstAuthentication() {
    Optional<Authentication> authenticationOptional  = authenticationRepository.findFirstAuthentication();
    if(authenticationOptional.isEmpty()){
      return new FactusTokenResponse();
    }
    Authentication authentication = authenticationOptional.get();
    FactusTokenResponse factusTokenResponse = webClientService.authenticationCreate(authentication);
    assert factusTokenResponse != null;
    return authenticationMapper.factusResponseToAuthorizationTokenCreate(factusTokenResponse);
  }

  private FactusTokenResponse AuthenticationRefreshToken() {
    Optional<Authentication> authenticationOptional  = authenticationRepository.findFirstAuthentication();
    if(authenticationOptional.isEmpty()){
      return new FactusTokenResponse();
    }
    Authentication authentication = authenticationOptional.get();
    authentication.setGran_type("refresh_token");
    Optional<AuthorizationToken> authorizationTokenOptional = authorizationTokenRepository.findFirstAuthentication();
    AuthorizationToken token = authorizationTokenOptional.get();
    FactusTokenResponse factusTokenResponse = webClientService.authenticationRefresh(authentication,token);
    assert factusTokenResponse != null;
    return authenticationMapper.factusResponseToAuthorizationTokenRefresh(factusTokenResponse,token);
  }
}
