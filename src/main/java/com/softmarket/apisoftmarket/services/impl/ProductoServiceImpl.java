package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.dto.*;
import com.softmarket.apisoftmarket.entity.Categoria;
import com.softmarket.apisoftmarket.entity.Marca;
import com.softmarket.apisoftmarket.entity.Producto;
import com.softmarket.apisoftmarket.exception.ProductoException;
import com.softmarket.apisoftmarket.mapper.ProductoMapper;
import com.softmarket.apisoftmarket.repository.ProductoRepository;
import com.softmarket.apisoftmarket.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

  private final ProductoRepository productoRepository;
  private final ProductoMapper productoMapper;
  private final InventarioService inventarioService;
  private final MarcaService marcaService;
  private final CategoriaService categoriaService;

  public ProductoServiceImpl(ProductoRepository productoRepository, ProductoMapper productoMapper, InventarioService inventarioService,  MarcaService marcaService, CategoriaService categoriaService) {
    this.productoRepository = productoRepository;
    this.productoMapper = productoMapper;
    this.inventarioService = inventarioService;
    this.marcaService = marcaService;
    this.categoriaService = categoriaService;
  }

  @Override
  public ResponseEntity<GenericResponse> crearProducto(ProductoRequest productoRequest) {
    Marca marca = marcaService.obtenerMarcaNombre(productoRequest.getMarca());
    Categoria categoria = categoriaService.obtenerCategoriaNombre(productoRequest.getCategoria());
    Producto producto = productoMapper.requestToEntityCreate(productoRequest,marca,categoria);
    producto  = productoRepository.save(producto);
    inventarioService.crearInventario(producto.getId(),productoRequest.getStockMinimo());
    return ResponseEntity.status(HttpStatus.CREATED).body(new GenericResponse(HttpStatus.CREATED.value(), "Producto creado con éxito"));
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
    if(productosResponse.isEmpty()){
      throw new ProductoException("Lista de productos vacia");
    }
    return ResponseEntity.status(HttpStatus.OK).body(productosResponse);
  }

  @Override
  public ResponseEntity<ProductoResponse> obtenerPorId(Long id) {
    return productoRepository.findById(id)
            .map(producto -> {
              ProductoResponse productoResponse = productoMapper.entityToResponse(producto);
              return ResponseEntity.status(HttpStatus.OK).body(productoResponse);
            })
            .orElseThrow(()-> new ProductoException("Producto no encontrado"));
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
              return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse(HttpStatus.OK.value(), "Producto actualizado con éxito"));
            })
            .orElseThrow(()->new ProductoException("Producto no encontrado"));
  }
}
