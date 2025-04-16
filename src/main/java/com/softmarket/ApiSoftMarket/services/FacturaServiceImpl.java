package com.softmarket.ApiSoftMarket.services;

import com.softmarket.ApiSoftMarket.dto.FacturaDto;
import com.softmarket.ApiSoftMarket.dto.FacturaRequest;
import com.softmarket.ApiSoftMarket.entity.ExternalApiProperties;
import com.softmarket.ApiSoftMarket.entity.Factura;
import com.softmarket.ApiSoftMarket.entity.FacturaResponse;
import com.softmarket.ApiSoftMarket.entity.FactusTokenResponse;
import com.softmarket.ApiSoftMarket.repository.FacturaRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class FacturaServiceImpl implements FacturaService{

  private final AuthenticationService authenticationService;
  private final WebClient.Builder webClientBuilder;
  private final FacturaRepository facturaRepository;
  private final ExternalApiProperties externalApiProperties;

  public FacturaServiceImpl(AuthenticationService authenticationService, WebClient.Builder webClientBuilder, FacturaRepository facturaRepository, ExternalApiProperties externalApiProperties) {
    this.authenticationService = authenticationService;
    this.webClientBuilder = webClientBuilder;
    this.facturaRepository = facturaRepository;
    this.externalApiProperties = externalApiProperties;
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
      FacturaDto facturaDto = new FacturaDto(
              responseFactus.getStatus(),
              responseFactus.getData().getBill().getCufe(),
              responseFactus.getData().getBill().getNumber(),
              responseFactus.getData().getBill().getReference_code()
      );
      Factura factura = new Factura(
              facturaDto.getCufe(),
              facturaDto.getNumber(),
              facturaDto.getReference_code()
      );
      facturaRepository.save(factura);
      return ResponseEntity.ok(facturaDto);
    }catch(Exception e){
      return ResponseEntity.ok(new FacturaDto(e.getMessage()));
    }
  }
}
