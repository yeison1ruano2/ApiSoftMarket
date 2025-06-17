package com.softmarket.apisoftmarket.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softmarket.apisoftmarket.dto.FacturaDto;
import com.softmarket.apisoftmarket.dto.FacturaRequest;
import com.softmarket.apisoftmarket.entity.ErrorFactura;
import com.softmarket.apisoftmarket.entity.Factura;
import com.softmarket.apisoftmarket.dto.FacturaResponse;
import com.softmarket.apisoftmarket.exception.FacturaException;
import com.softmarket.apisoftmarket.repository.ErrorFacturaRepository;
import com.softmarket.apisoftmarket.repository.FacturaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class FacturaMapper {

  private final FacturaRepository facturaRepository;
  private final ErrorFacturaRepository errorFacturaRepository;
  private final ObjectMapper objectMapper = new ObjectMapper();

  public FacturaMapper(FacturaRepository facturaRepository, ErrorFacturaRepository errorFacturaRepository) {
    this.facturaRepository = facturaRepository;
    this.errorFacturaRepository = errorFacturaRepository;
  }

  public FacturaDto responseFactusToDto(FacturaResponse responseFactus) {
    FacturaDto facturaDto = new FacturaDto(
            responseFactus.getStatus(), responseFactus.getData().getBill().getCufe(),
            responseFactus.getData().getBill().getNumber(), responseFactus.getData().getBill().getReference_code(),
            ""
    );
    Factura factura = new Factura(
            facturaDto.getCufe(), facturaDto.getNumber(),
            facturaDto.getReference_code()
    );
    facturaRepository.save(factura);
    return facturaDto;
  }

  public FacturaDto exceptionFacturaSave(FacturaException e, FacturaRequest facturaRequest, String message) throws JsonProcessingException {
    String facturaJson = objectMapper.writeValueAsString(facturaRequest);
    ErrorFactura errorFactura = new ErrorFactura(facturaJson, message);
    errorFacturaRepository.save(errorFactura);
    return new FacturaDto(
            e.getStatus().getReasonPhrase(), "", "",
            facturaRequest.getReference_code(), message
    );
  }

  public FacturaDto exceptionFactura500Save(Exception e, FacturaRequest facturaRequest) throws JsonProcessingException {
    String facturaJson = objectMapper.writeValueAsString(facturaRequest);
    ErrorFactura errorFactura = new ErrorFactura(
            facturaJson,e.getMessage()
    );
    errorFacturaRepository.save(errorFactura);
    return new FacturaDto(
            HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "","",
            facturaRequest.getReference_code(),e.getMessage()
    );
  }
}
