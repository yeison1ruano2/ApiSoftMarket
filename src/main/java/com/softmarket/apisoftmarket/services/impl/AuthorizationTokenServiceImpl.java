package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.dto.DataRangoEnumeracionFactusResponse;
import com.softmarket.apisoftmarket.dto.FactusTokenResponse;
import com.softmarket.apisoftmarket.entity.Authentication;
import com.softmarket.apisoftmarket.entity.AuthorizationToken;
import com.softmarket.apisoftmarket.entity.RangosEnumeracion;
import com.softmarket.apisoftmarket.exception.AuthorizationTokenException;
import com.softmarket.apisoftmarket.exception.RangoEnumeracionException;
import com.softmarket.apisoftmarket.mapper.AuthorizationTokenMapper;
import com.softmarket.apisoftmarket.repository.AuthorizationTokenRepository;
import com.softmarket.apisoftmarket.services.AuthorizationTokenService;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationTokenServiceImpl implements AuthorizationTokenService {

  private final AuthorizationTokenMapper authorizationTokenMapper;
  private final AuthorizationTokenRepository authorizationTokenRepository;
  private final WebClientService webClientService;

  public AuthorizationTokenServiceImpl(AuthorizationTokenMapper authorizationTokenMapper, AuthorizationTokenRepository authorizationTokenRepository, WebClientService webClientService) {
    this.authorizationTokenMapper = authorizationTokenMapper;
    this.authorizationTokenRepository = authorizationTokenRepository;
    this.webClientService = webClientService;
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

  @Override
  public AuthorizationToken obtenerRangoEnumeracion(String accessToken,AuthorizationToken authorizationToken) {
    DataRangoEnumeracionFactusResponse dataRangoEnumeracionFactusResponse =  webClientService.buscarCrearRangoEnumeracion(accessToken);
    RangosEnumeracion rangosEnumeracion = dataRangoEnumeracionFactusResponse.getData()
            .stream()
            .filter(doc -> "Factura de Venta".equals(doc.getDocument()))
            .findFirst()
            .orElseThrow(()-> new RangoEnumeracionException("No existe el rango de enumeración para Factura de Venta"));
    AuthorizationToken authorizationToken1;
    authorizationToken.setRangoEnumeracionVenta(rangosEnumeracion.getId().toString());
    authorizationToken1 = authorizationTokenRepository.save(authorizationToken);
    return authorizationToken1;
  }
}
