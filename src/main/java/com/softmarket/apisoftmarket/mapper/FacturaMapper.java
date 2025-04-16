package com.softmarket.apisoftmarket.mapper;

import com.softmarket.apisoftmarket.dto.FacturaDto;
import com.softmarket.apisoftmarket.entity.Factura;
import com.softmarket.apisoftmarket.entity.FacturaResponse;
import com.softmarket.apisoftmarket.repository.FacturaRepository;
import org.springframework.stereotype.Service;

@Service
public class FacturaMapper {

  private final FacturaRepository facturaRepository;

  public FacturaMapper(FacturaRepository facturaRepository) {
    this.facturaRepository = facturaRepository;
  }

  public FacturaDto responseFactusToDto(FacturaResponse responseFactus) {
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
    return facturaDto;
  }
}
