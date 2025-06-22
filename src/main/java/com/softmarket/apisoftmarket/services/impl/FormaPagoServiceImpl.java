package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.dto.FormaPagoResponse;
import com.softmarket.apisoftmarket.exception.GenericException;
import com.softmarket.apisoftmarket.exception.ProductoException;
import com.softmarket.apisoftmarket.mapper.FormaPagoMapper;
import com.softmarket.apisoftmarket.repository.FormaPagoRepository;
import com.softmarket.apisoftmarket.services.FormaPagoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormaPagoServiceImpl implements FormaPagoService {

  private final FormaPagoRepository formaPagoRepository;
  private final FormaPagoMapper formaPagoMapper;

  public FormaPagoServiceImpl(FormaPagoRepository formaPagoRepository, FormaPagoMapper formaPagoMapper) {
    this.formaPagoRepository = formaPagoRepository;
    this.formaPagoMapper = formaPagoMapper;
  }

  @Override
  public ResponseEntity<List<FormaPagoResponse>> listarTodos() {
    List<FormaPagoResponse> formaPagosList = formaPagoRepository.findAll()
            .stream()
            .map(formaPagoMapper::entityToResponse)
            .toList();
    if(formaPagosList.isEmpty()){
      throw new ProductoException("No existencias");
    }
    return ResponseEntity.status(HttpStatus.OK).body(formaPagosList);

  }

  @Override
  public ResponseEntity<FormaPagoResponse> listarPorCodigo(String codigo) {
    return formaPagoRepository.findByCodigo(codigo)
            .map(formaPago ->{
              FormaPagoResponse formaPagoResponse = formaPagoMapper.entityToResponse(formaPago);
              return ResponseEntity.status(HttpStatus.OK).body(formaPagoResponse);
            })
            .orElseThrow(()-> new GenericException("No existencias"));
  }
}
