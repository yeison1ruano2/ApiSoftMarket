package com.softmarket.ApiSoftMarket.services;

import com.softmarket.ApiSoftMarket.entity.Authentication;
import com.softmarket.ApiSoftMarket.entity.AuthorizationToken;
import com.softmarket.ApiSoftMarket.entity.ExternalApiProperties;
import com.softmarket.ApiSoftMarket.entity.FactusTokenResponse;
import com.softmarket.ApiSoftMarket.repository.AuthenticationRepository;
import com.softmarket.ApiSoftMarket.repository.AuthorizationTokenRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements  AuthenticationService{

  private final AuthenticationRepository authenticationRepository;
  private final AuthorizationTokenRepository authorizationTokenRepository;
  private final WebClient.Builder webClientBuilder;
  private final ExternalApiProperties externalApiProperties;

  public AuthenticationServiceImpl(AuthenticationRepository authenticationRepository, AuthorizationTokenRepository authorizationTokenRepository, WebClient.Builder webClientBuilder, ExternalApiProperties externalApiProperties) {
    this.authenticationRepository = authenticationRepository;
    this.authorizationTokenRepository = authorizationTokenRepository;
    this.webClientBuilder = webClientBuilder;
    this.externalApiProperties = externalApiProperties;
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
    return new FactusTokenResponse(
            token.getAccess_token(),
            token.getExpires_in(),
            token.getRefresh_token(),
            token.getToken_type()
    );
  }

  private FactusTokenResponse firstAuthentication() {
    Optional<Authentication> authenticationOptional  = authenticationRepository.findFirstAuthentication();
    if(authenticationOptional.isEmpty()){
      return new FactusTokenResponse();
    }
    Authentication authentication = authenticationOptional.get();
    FactusTokenResponse factusTokenResponse =  webClientBuilder.build()
            .post()
            .uri(externalApiProperties.getAuthUrl())
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .body(BodyInserters
                    .fromFormData("grant_type","password")
                    .with("client_id",authentication.getClient_id())
                    .with("client_secret",authentication.getClient_secret())
                    .with("username",authentication.getUsername())
                    .with("password",authentication.getPassword()))
            .retrieve()
            .bodyToMono(FactusTokenResponse.class)
            .block();

    assert factusTokenResponse != null;
    AuthorizationToken authorizationToken = new AuthorizationToken(
            factusTokenResponse.getAccess_token(),
            factusTokenResponse.getExpires_in(),
            factusTokenResponse.getRefresh_token(),
            factusTokenResponse.getToken_type()
    );
    authorizationTokenRepository.save(authorizationToken);
    return factusTokenResponse;
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
    FactusTokenResponse factusTokenResponse =  webClientBuilder.build()
            .post()
            .uri(externalApiProperties.getAuthUrl())
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .body(BodyInserters
                    .fromFormData("grant_type",authentication.getGran_type())
                    .with("client_id",authentication.getClient_id())
                    .with("client_secret",authentication.getClient_secret())
                    .with("refresh_token",token.getRefresh_token()))
            .retrieve()
            .bodyToMono(FactusTokenResponse.class)
            .block();

    assert factusTokenResponse != null;
    AuthorizationToken authorizationToken = new AuthorizationToken(
            factusTokenResponse.getAccess_token(),
            factusTokenResponse.getExpires_in(),
            factusTokenResponse.getRefresh_token(),
            factusTokenResponse.getToken_type()
    );
    authorizationTokenRepository.save(authorizationToken);
    return factusTokenResponse;
  }
}
