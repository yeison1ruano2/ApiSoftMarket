package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.dto.*;
import com.softmarket.apisoftmarket.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreconfiguradoServiceImpl implements PreconfiguradoService {

  private final MarcaService marcaService;
  private final CategoriaService categoriaService;
  private final InventarioService inventarioService;
  private final FormaPagoService formaPagoService;

  public PreconfiguradoServiceImpl(MarcaService marcaService, CategoriaService categoriaService, InventarioService inventarioService, FormaPagoService formaPagoService) {
    this.marcaService = marcaService;
    this.categoriaService = categoriaService;
    this.inventarioService = inventarioService;
    this.formaPagoService = formaPagoService;
  }

  @Override
  public ResponseEntity<PreconfiguradoResponse> obtenerPreconfigurado() {
    List<String> marcaResponseList = marcaService.obtenerMarcasList();
    List<String> categoriaResponseList = categoriaService.obtenerCategoriaList();
    List<ProductoResponse> productoResponsesList = inventarioService.obtenerProductoList();
    List<String> metodosDePagosList = formaPagoService.obtenerMetodosDePagoList();
    return ResponseEntity.status(HttpStatus.OK).body(
            new PreconfiguradoResponse(marcaResponseList,categoriaResponseList,productoResponsesList,metodosDePagosList)
    );
  }
}
