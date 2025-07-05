package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.dto.DataRangoEnumeracionFactusResponse;
import com.softmarket.apisoftmarket.entity.RangosEnumeracion;
import com.softmarket.apisoftmarket.exception.RangoEnumeracionException;
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
  public Long buscarCrearRangoEnumeracion(String token) {
      Long rangoEnumeracionLong = rangoEnumeracionVenta();
      if(rangoEnumeracionLong !=null){
        return rangoEnumeracionLong;
      }
      DataRangoEnumeracionFactusResponse dataRangoEnumeracionFactusResponse = webClientService.buscarCrearRangoEnumeracion(token);
      RangosEnumeracion rangosEnumeracion = dataRangoEnumeracionFactusResponse.getData()
              .stream()
              .filter(doc -> "Factura de Venta".equals(doc.getDocument()))
              .findFirst()
              .orElseThrow(()-> new RangoEnumeracionException("No existe el rango de enumeracion para Factura de Venta"));
      rangosEnumeracion = rangosEnumeracionRepository.save(rangosEnumeracion);
      return rangosEnumeracion.getId();
  }

  @Override
  public Long rangoEnumeracionVenta() {
    return rangosEnumeracionRepository.findByFacturaVenta();
  }
}
