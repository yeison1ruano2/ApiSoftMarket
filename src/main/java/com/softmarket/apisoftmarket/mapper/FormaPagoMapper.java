package com.softmarket.apisoftmarket.mapper;

import com.softmarket.apisoftmarket.dto.FormaPagoResponse;
import com.softmarket.apisoftmarket.entity.CodigosFormasPago;
import org.springframework.stereotype.Service;

@Service
public class FormaPagoMapper {
  public FormaPagoResponse entityToResponse(CodigosFormasPago codigosFormasPago) {
    return new FormaPagoResponse(
            codigosFormasPago.getCodigo(),
            codigosFormasPago.getNombre()
    );
  }
}
