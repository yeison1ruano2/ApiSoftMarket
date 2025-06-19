package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.dto.CategoriaRequest;
import com.softmarket.apisoftmarket.dto.CategoriaResponse;
import com.softmarket.apisoftmarket.dto.GenericResponse;
import com.softmarket.apisoftmarket.entity.Categoria;
import com.softmarket.apisoftmarket.exception.CategoriaException;
import com.softmarket.apisoftmarket.mapper.CategoriaMapper;
import com.softmarket.apisoftmarket.repository.CategoriaRepository;
import com.softmarket.apisoftmarket.services.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

  private final CategoriaMapper categoriaMapper;
  private final CategoriaRepository categoriaRepository;

  public CategoriaServiceImpl(CategoriaMapper categoriaMapper, CategoriaRepository categoriaRepository) {
    this.categoriaMapper = categoriaMapper;
    this.categoriaRepository = categoriaRepository;
  }

  @Override
  public ResponseEntity<GenericResponse> crearCategoria(CategoriaRequest categoriaRequest) {
    Categoria categoria = categoriaMapper.requestToEntityCreate(categoriaRequest);
    categoriaRepository.save(categoria);
    return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse(HttpStatus.OK.value(), "Categoria creada con éxito"));
  }

  @Override
  public ResponseEntity<List<CategoriaResponse>> obtenerCategoriaNombreList(String nombre) {
    List<CategoriaResponse> categoriaResponses = categoriaRepository.findByNombreList(nombre)
            .stream()
            .map(categoriaMapper::entityToResponse)
            .toList();
    return ResponseEntity.status(HttpStatus.OK).body(categoriaResponses);

  }

  @Override
  public Categoria obtenerCategoriaNombre(String nombre) {
    return categoriaRepository.findByNombre(nombre).orElseThrow(()->new CategoriaException("Categoria no encontrada"));
  }

  @Override
  public ResponseEntity<GenericResponse> actualizarCategoria(Integer idInteger, CategoriaRequest categoriaRequest) {
    Long id = idInteger.longValue();
    return categoriaRepository.findById(id)
            .map(categoria ->{
              categoria = categoriaMapper.requestToEntityUpdate(categoria,categoriaRequest);
              categoriaRepository.save(categoria);
              return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse(HttpStatus.OK.value(), "Categoria actualizada con éxito"));
            })
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponse(HttpStatus.NOT_FOUND.value(),"Categoria no encontrado")));
  }


}
