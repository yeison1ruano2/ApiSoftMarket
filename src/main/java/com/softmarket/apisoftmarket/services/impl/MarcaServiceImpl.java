package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.dto.GenericResponse;
import com.softmarket.apisoftmarket.dto.MarcaRequest;
import com.softmarket.apisoftmarket.dto.MarcaResponse;
import com.softmarket.apisoftmarket.entity.Marca;
import com.softmarket.apisoftmarket.exception.MarcaException;
import com.softmarket.apisoftmarket.mapper.MarcaMapper;
import com.softmarket.apisoftmarket.repository.MarcaRepository;
import com.softmarket.apisoftmarket.services.MarcaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcaServiceImpl implements MarcaService {

  private final MarcaMapper marcaMapper;
  private final MarcaRepository marcaRepository;

  public MarcaServiceImpl(MarcaMapper marcaMapper, MarcaRepository marcaRepository) {
    this.marcaMapper = marcaMapper;
    this.marcaRepository = marcaRepository;
  }

  @Override
  public ResponseEntity<GenericResponse> crearMarca(MarcaRequest marcaRequest) {
    Marca marca = marcaMapper.requestToEntityCreate(marcaRequest);
    marcaRepository.save(marca);
    return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse(HttpStatus.OK.getReasonPhrase(),"Marca creada con éxito"));
  }

  @Override
  public ResponseEntity<GenericResponse> actualizarMarca(String id, MarcaRequest marcaRequest) {
    return marcaRepository.findById(Long.parseLong(id))
            .map(marca -> {
              marca = marcaMapper.requestToEntityUpdate(marca,marcaRequest);
              marcaRepository.save(marca);
              return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse(HttpStatus.OK.getReasonPhrase(),"Marca actualiazada con éxito"));
            })
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponse(HttpStatus.NOT_FOUND.getReasonPhrase(),"Marca no encontrada")));
  }

  @Override
  public ResponseEntity<List<MarcaResponse>> obtenerMarcas() {
    List<MarcaResponse> marcaResponses =  marcaRepository.findAll()
            .stream()
            .map(marcaMapper::entityToResponse)
            .toList();

    return ResponseEntity.status(HttpStatus.OK).body(marcaResponses);
  }

  @Override
  public ResponseEntity<List<MarcaResponse>> obtenerMarcaNombreList(String nombre) {
    List<MarcaResponse> marcaResponses = marcaRepository.findByNombreList(nombre)
            .stream()
            .map(marcaMapper::entityToResponse)
            .toList();
    return ResponseEntity.status(HttpStatus.OK).body(marcaResponses);
  }

  @Override
  public Marca obtenerMarcaNombre(String nombre) {
    return marcaRepository.findByNombre(nombre).orElseThrow(() -> new MarcaException("Marca no encontrada"));
  }
}
