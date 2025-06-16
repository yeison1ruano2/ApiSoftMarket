package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.dto.MetodoDePagoResponse;
import com.softmarket.apisoftmarket.entity.MetodoDePago;
import com.softmarket.apisoftmarket.exception.MetodoPagoException;
import com.softmarket.apisoftmarket.mapper.MetodoDePagoMapper;
import com.softmarket.apisoftmarket.repository.MetodoDePagoRepository;
import com.softmarket.apisoftmarket.services.MetodoDePagoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetodoDePagoServiceImpl implements MetodoDePagoService {

  private final MetodoDePagoRepository metodoDePagoRepository;
  private final MetodoDePagoMapper metodoDePagoMapper;

  public MetodoDePagoServiceImpl(MetodoDePagoRepository metodoDePagoRepository, MetodoDePagoMapper metodoDePagoMapper) {
    this.metodoDePagoRepository = metodoDePagoRepository;
    this.metodoDePagoMapper = metodoDePagoMapper;
  }

  @Override
  public ResponseEntity<List<MetodoDePagoResponse>> listarMetodosDePago() {
    List<MetodoDePago> metodoDePagoList =  metodoDePagoRepository.findAll();
    List<MetodoDePagoResponse> metodoDePagoResponseList = metodoDePagoList
            .stream()
            .map(metodoDePagoMapper::entityToResponse)
            .toList();
    return ResponseEntity.status(HttpStatus.OK).body(metodoDePagoResponseList);
  }

  @Override
  public ResponseEntity<MetodoDePagoResponse> listarMetodoDePagoTermino(String termino) {
    MetodoDePago metodoDePago = metodoDePagoRepository.buscarPorTermino(termino).orElseThrow(()->new MetodoPagoException("Metodo de pago no encontrado"));
    MetodoDePagoResponse metodoDePagoResponse = metodoDePagoMapper.entityToResponse(metodoDePago);
    return ResponseEntity.status(HttpStatus.OK).body(metodoDePagoResponse);
  }

  @Override
  public MetodoDePago obtenerMetodoPagoTermino(String termino) {
    return metodoDePagoRepository.buscarPorTermino(termino).orElseThrow(()->new MetodoPagoException("Metodo de pago no encontrado"));
  }
}
