package com.softmarket.apisoftmarket.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softmarket.apisoftmarket.dto.FacturaDto;
import com.softmarket.apisoftmarket.dto.FacturaPdfFactusResponse;
import com.softmarket.apisoftmarket.dto.FacturaRequest;
import com.softmarket.apisoftmarket.entity.ErrorFactura;
import com.softmarket.apisoftmarket.entity.Factura;
import com.softmarket.apisoftmarket.dto.FacturaResponse;
import com.softmarket.apisoftmarket.exception.Factura409Exception;
import com.softmarket.apisoftmarket.exception.Factura422Exception;
import com.softmarket.apisoftmarket.repository.ErrorFacturaRepository;
import com.softmarket.apisoftmarket.repository.FacturaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Map;

@Service
public class FacturaMapper {

  private final FacturaRepository facturaRepository;
  private final ErrorFacturaRepository errorFacturaRepository;
  private final ObjectMapper objectMapper = new ObjectMapper();
  private static final Logger logger = LoggerFactory.getLogger(FacturaMapper.class);

  public FacturaMapper(FacturaRepository facturaRepository, ErrorFacturaRepository errorFacturaRepository) {
    this.facturaRepository = facturaRepository;
    this.errorFacturaRepository = errorFacturaRepository;
  }

  public FacturaDto responseFactusToDto(FacturaResponse responseFactus) {
    var bill = responseFactus.getData().getBill();
    FacturaDto facturaDto = new FacturaDto(
            HttpStatus.OK.value(),
            bill.getCufe(),
            bill.getNumber(),
            bill.getReference_code(),
            "",null
    );
    facturaRepository.save(new Factura(facturaDto.getCufe(), facturaDto.getNumber(), facturaDto.getReference_code()));
    return facturaDto;
  }

  public FacturaDto responseFactusToDtoV3(FacturaResponse responseFactus, FacturaPdfFactusResponse facturaPdfFactusResponse) {
    var bill = responseFactus.getData().getBill();
    FacturaDto facturaDto = new FacturaDto(
            HttpStatus.OK.value(),
            bill.getCufe(),
            bill.getNumber(),
            bill.getReference_code(),
            "",null
    );
    Factura factura = new Factura(
            facturaDto.getCufe(),
            facturaDto.getNumber(),
            facturaDto.getReference_code(),
            facturaPdfFactusResponse.getData().getFile_name(),
            facturaPdfFactusResponse.getData().getPdf_base_64_encoded()
    );
    facturaRepository.save(factura);
    return facturaDto;
  }

  public FacturaDto exceptionFacturaSave(Factura422Exception e, FacturaRequest facturaRequest, String message){
    return guardarErrorYConstruirDto(facturaRequest,message,e.getStatus(),e.getErrors());
  }

  public FacturaDto exceptionFacturaSave(Factura409Exception e, FacturaRequest facturaRequest, String message){
    return guardarErrorYConstruirDto(facturaRequest,message,e.getStatus(),null);
  }

  private FacturaDto guardarErrorYConstruirDto(FacturaRequest facturaRequest, String message, HttpStatus status, Map<String, List<String>> errors) {
    try {
      String facturaJson = objectMapper.writeValueAsString(facturaRequest);
      logger.info("Errores en guardarErrorYConstruirDto");
      errorFacturaRepository.save(new ErrorFactura(facturaJson,message));
    }catch (JsonProcessingException ex){
      errorFacturaRepository.save(new ErrorFactura("Error serializando factura.",message));
    }
    return new FacturaDto(
            status.value(),
            "","",facturaRequest.getReference_code(),
            message,errors
    );
  }

  public FacturaDto exceptionFactura500Save(Exception e, FacturaRequest facturaRequest){
    return guardarErrorYConstruirDto(facturaRequest,e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,null);
  }

  public FacturaDto entityToDto(Factura factura) {
    return new FacturaDto(
            HttpStatus.OK.value(),
            factura.getCufe(),
            factura.getNumber(),
            factura.getReference_code(),
            "",null
    );
  }
}
