package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.dto.*;
import com.softmarket.apisoftmarket.entity.Categoria;
import com.softmarket.apisoftmarket.entity.Marca;
import com.softmarket.apisoftmarket.entity.Producto;
import com.softmarket.apisoftmarket.mapper.CategoriaMapper;
import com.softmarket.apisoftmarket.mapper.MarcaMapper;
import com.softmarket.apisoftmarket.mapper.ProductoMapper;
import com.softmarket.apisoftmarket.repository.ProductoRepository;
import com.softmarket.apisoftmarket.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductoServiceImpl implements ProductoService {

  private final ProductoRepository productoRepository;
  private final ProductoMapper productoMapper;
  private final InventarioService inventarioService;
  private final CategoriaMapper categoriaMapper;
  private final MarcaMapper marcaMapper;
  private final MarcaService marcaService;
  private final CategoriaService categoriaService;

  public ProductoServiceImpl(ProductoRepository productoRepository, ProductoMapper productoMapper, InventarioService inventarioService, CategoriaMapper categoriaMapper, MarcaMapper marcaMapper, MarcaService marcaService, CategoriaService categoriaService) {
    this.productoRepository = productoRepository;
    this.productoMapper = productoMapper;
    this.inventarioService = inventarioService;
    this.categoriaMapper = categoriaMapper;
    this.marcaMapper = marcaMapper;
    this.marcaService = marcaService;
    this.categoriaService = categoriaService;
  }

  @Override
  public ResponseEntity<ProductoResponse> crearProducto(ProductoRequest productoRequest) {
    Marca marca = marcaService.obtenerMarcaNombre(productoRequest.getMarca());
    Categoria categoria = categoriaService.obtenerCategoriaNombre(productoRequest.getCategoria());
    Producto producto = productoMapper.requestToEntityCreate(productoRequest,marca,categoria);
    producto  = productoRepository.save(producto);
    InventarioResponse inventarioResponse = inventarioService.crearStock(producto.getId(),productoRequest.getStockMinimo());
    if(Objects.equals(inventarioResponse.getStatus(), HttpStatus.NOT_FOUND.getReasonPhrase())){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ProductoResponse(inventarioResponse.getStatus(),inventarioResponse.getMensaje()));
    }
    MarcaResponse marcaResponse = marcaMapper.entityToResponse(marca);
    CategoriaResponse categoriaResponse = categoriaMapper.entityToResponse(categoria);
    ProductoResponse productoResponse = productoMapper.entityToResponseCreate(producto,marcaResponse,categoriaResponse);
    return ResponseEntity.status(HttpStatus.CREATED).body(productoResponse);
  }

  /*@Override
  public ResponseEntity<GenericResponse> ingresarStock(String codigoBarras, Integer cantidad) {
    return productoRepository.findByCodigoBarras(codigoBarras)
            .map(producto -> {
              Inventario inventario = inventarioService.actualizarCantidadInventario(producto.getId(),cantidad);
              movimientoInventarioService.crearEntrada(inventario,cantidad);
              return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse(HttpStatus.OK.getReasonPhrase(),""));
            })
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponse(HttpStatus.NOT_FOUND.getReasonPhrase(),"No existe producto con ese ID")));
  }*/

  @Override
  public ResponseEntity<List<ProductoResponse>> listarTodos() {
    List<ProductoResponse> productosResponse = productoRepository.findAll()
            .stream()
            .map(productoMapper::entityToResponse)
            .toList();
    return ResponseEntity.status(HttpStatus.OK).body(productosResponse);
  }

  @Override
  public ResponseEntity<ProductoResponse> obtenerPorId(Long id) {
    return productoRepository.findById(id)
            .map(producto -> {
              ProductoResponse productoResponse = productoMapper.entityToResponse(producto);
              return ResponseEntity.status(HttpStatus.OK).body(productoResponse);
            })
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ProductoResponse(HttpStatus.NOT_FOUND.getReasonPhrase(),"No se encontro producto con el ID")));
  }

  @Override
  public ResponseEntity<List<ProductoResponse>> obtenerProductoNombre(String nombre) {
    List<ProductoResponse> productoResponses = productoRepository.findByNombre(nombre)
            .stream()
            .map(productoMapper::entityToResponse)
            .toList();
    return ResponseEntity.status(HttpStatus.OK).body(productoResponses);
  }


  @Override
  public ResponseEntity<List<ProductoResponse>> obtenerProductoBarras(String codigoBarras) {
    List<ProductoResponse> productResponses = productoRepository.findByCodigoBarras(codigoBarras)
            .stream()
            .map(productoMapper::entityToResponse)
            .toList();
    return ResponseEntity.status(HttpStatus.OK).body(productResponses);
  }

  @Override
  public ResponseEntity<GenericResponse> actualizarProducto(String codigoBarras, ProductoRequest productoRequest) {
    return productoRepository.findByCodigoBarras(codigoBarras)
            .map(producto -> {
              producto = productoMapper.requestToEntityUpdate(producto,productoRequest);
              productoRepository.save(producto);
              return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse(HttpStatus.OK.getReasonPhrase(), "Producto actualizado con éxito"));
            })
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponse(HttpStatus.NOT_FOUND.getReasonPhrase(),"Producto no encontrado")));
  }
}
