package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.entity.AuthorizationToken;
import com.softmarket.apisoftmarket.dto.FactusTokenResponse;
import com.softmarket.apisoftmarket.mapper.AuthenticationMapper;
import com.softmarket.apisoftmarket.repository.AuthenticationRepository;
import com.softmarket.apisoftmarket.repository.AuthorizationTokenRepository;
import com.softmarket.apisoftmarket.services.AuthenticationService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

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
  public FactusTokenResponse  authenticationFactus() {

    return authorizationTokenRepository.findFirstAuthentication()
            .filter(token -> token.getExpiration_time().isAfter(LocalDateTime.now()))
            .map(authenticationMapper::tokenToFactusResponse)
            .orElseGet(this::handleMissingOrExpiredToken);
  }

  private FactusTokenResponse handleMissingOrExpiredToken(){
    return authenticationRepository.findFirstAuthentication()
            .map(auth -> {
              AuthorizationToken existingToken = authorizationTokenRepository.findFirstAuthentication().orElseThrow(null);
              if(existingToken != null && existingToken.getExpiration_time().isBefore(LocalDateTime.now())){
                auth.setGran_type("refresh_token");
                FactusTokenResponse refreshed = webClientService.authenticationRefresh(auth,existingToken);
                return refreshed != null
                        ? authenticationMapper.factusResponseToAuthorizationTokenRefresh(refreshed,existingToken)
                        : new FactusTokenResponse();
              }

              FactusTokenResponse created = webClientService.authenticationCreate(auth);
              return created != null
                      ? authenticationMapper.factusResponseToAuthorizationTokenCreate(created)
                      : new FactusTokenResponse();
            })
            .orElse(new FactusTokenResponse());
  }
}
