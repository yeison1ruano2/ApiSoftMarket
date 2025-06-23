package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.dto.DataRangoEnumeracionFactusResponse;
import com.softmarket.apisoftmarket.repository.RangosEnumeracionRepository;
import com.softmarket.apisoftmarket.services.RangoEnumeracionService;
import org.springframework.stereotype.Service;

@Service
public class RangoEnumeracionServiceImpl implements RangoEnumeracionService {

  private final WebClientService webClientService;
  private final RangosEnumeracionRepository rangosEnumeracionRepository;

  public RangoEnumeracionServiceImpl(WebClientService webClientService, RangosEnumeracionRepository rangosEnumeracionRepository) {
    this.webClientService = webClientService;
    this.rangosEnumeracionRepository = rangosEnumeracionRepository;
  }

  @Override
  public void buscarCrearRangoEnumeracion() {
      DataRangoEnumeracionFactusResponse dataRangoEnumeracionFactusResponse = webClientService.buscarCrearRangoEnumeracion();
  }

  @Override
  public Long rangoEnumeracionVenta() {
    return rangosEnumeracionRepository.findByFacturaVenta();
  }
}
