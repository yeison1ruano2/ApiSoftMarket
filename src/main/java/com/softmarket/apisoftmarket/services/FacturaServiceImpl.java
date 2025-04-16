package com.softmarket.apisoftmarket.services;

import com.softmarket.apisoftmarket.dto.FacturaDto;
import com.softmarket.apisoftmarket.dto.FacturaRequest;
import com.softmarket.apisoftmarket.entity.ExternalApiProperties;
import com.softmarket.apisoftmarket.entity.FacturaResponse;
import com.softmarket.apisoftmarket.entity.FactusTokenResponse;
import com.softmarket.apisoftmarket.mapper.FacturaMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class FacturaServiceImpl implements FacturaService{

  private final AuthenticationService authenticationService;
  private final WebClient.Builder webClientBuilder;
  private final ExternalApiProperties externalApiProperties;
  private final FacturaMapper facturaMapper;

  public FacturaServiceImpl(AuthenticationService authenticationService,
                            WebClient.Builder webClientBuilder,
                            ExternalApiProperties externalApiProperties,
                            FacturaMapper facturaMapper) {
    this.authenticationService = authenticationService;
    this.webClientBuilder = webClientBuilder;
    this.externalApiProperties = externalApiProperties;
    this.facturaMapper = facturaMapper;
  }

  @Override
  public ResponseEntity<FacturaDto> crearfactura(FacturaRequest facturaRequest) {
    FactusTokenResponse factusTokenResponse = authenticationService.authenticationFactus();
    //Crear factura en Factus
    try {
      FacturaResponse responseFactus =
              webClientBuilder.build()
                      .post()
                      .uri(externalApiProperties.getFacturaUrl())
                      .header(HttpHeaders.CONTENT_TYPE,"application/json")
                      .header(HttpHeaders.AUTHORIZATION, "Bearer " + factusTokenResponse.getAccess_token())
                      .bodyValue(facturaRequest)
                      .retrieve()
                      .bodyToMono(FacturaResponse.class)
                      .block();
      assert responseFactus != null;
      FacturaDto facturaDto = facturaMapper.responseFactusToDto(responseFactus);
      return ResponseEntity.ok(facturaDto);
    }catch(Exception e){
      return ResponseEntity.ok(new FacturaDto(e.getMessage()));
    }
  }
}
