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
    var bill = responseFactus.getData().getBill();
    FacturaDto facturaDto = new FacturaDto(
            responseFactus.getStatus(),
            bill.getCufe(),
            bill.getNumber(),
            bill.getReference_code(),
            ""
    );
    facturaRepository.save(new Factura(facturaDto.getCufe(), facturaDto.getNumber(), facturaDto.getReference_code()));
    return facturaDto;
  }

  public FacturaDto exceptionFacturaSave(FacturaException e, FacturaRequest facturaRequest, String message){
    return guardarErrorYConstruirDto(facturaRequest,message,e.getStatus());
  }

  private FacturaDto guardarErrorYConstruirDto(FacturaRequest facturaRequest, String message, HttpStatus status) {
    try {
      String facturaJson = objectMapper.writeValueAsString(facturaRequest);
      errorFacturaRepository.save(new ErrorFactura(facturaJson,message));
    }catch (JsonProcessingException ex){
      errorFacturaRepository.save(new ErrorFactura("Error serializando factura.",message));
    }
    return new FacturaDto(
            status.getReasonPhrase(),
            "","",facturaRequest.getReference_code(),
            message
    );
  }

  public FacturaDto exceptionFactura500Save(Exception e, FacturaRequest facturaRequest){
    return guardarErrorYConstruirDto(facturaRequest,e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
