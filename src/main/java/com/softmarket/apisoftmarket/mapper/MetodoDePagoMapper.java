package com.softmarket.apisoftmarket.mapper;

import com.softmarket.apisoftmarket.dto.MetodoDePagoResponse;
import com.softmarket.apisoftmarket.entity.MetodoDePago;
import org.springframework.stereotype.Service;

@Service
public class MetodoDePagoMapper {

  public MetodoDePagoResponse entityToResponse(MetodoDePago metodoDePago) {
    return new MetodoDePagoResponse(
        metodoDePago.getCodigo(),
        metodoDePago.getNombre());
  }
}
