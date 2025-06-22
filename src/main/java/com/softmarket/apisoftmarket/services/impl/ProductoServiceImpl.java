package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.dto.*;
import com.softmarket.apisoftmarket.entity.*;
import com.softmarket.apisoftmarket.exception.ProductoException;
import com.softmarket.apisoftmarket.mapper.ProductoMapper;
import com.softmarket.apisoftmarket.repository.ProductoRepository;
import com.softmarket.apisoftmarket.services.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class ProductoServiceImpl implements ProductoService {

  private final ProductoRepository productoRepository;
  private final ProductoMapper productoMapper;
  private final InventarioService inventarioService;
  private final MarcaService marcaService;
  private final CategoriaService categoriaService;
  private final IvaDataSheetService ivaDataSheetService;

  public ProductoServiceImpl(ProductoRepository productoRepository, ProductoMapper productoMapper, InventarioService inventarioService, MarcaService marcaService, CategoriaService categoriaService, IvaDataSheetService ivaDataSheetService) {
    this.productoRepository = productoRepository;
    this.productoMapper = productoMapper;
    this.inventarioService = inventarioService;
    this.marcaService = marcaService;
    this.categoriaService = categoriaService;
    this.ivaDataSheetService = ivaDataSheetService;
  }

  @Override
  public ResponseEntity<GenericResponse> crearProducto(ProductoRequest productoRequest) {
    try {
      Marca marca = marcaService.obtenerMarcaNombre(productoRequest.getMarca());
      Categoria categoria = categoriaService.obtenerCategoriaNombre(productoRequest.getCategoria());
      BigDecimal ivaProducto = ivaDataSheetService.buscarCoincidenciaCadena(productoRequest.getNombre());
      Producto producto = productoMapper.requestToEntityCreate(productoRequest,marca,categoria,ivaProducto);
      producto  = productoRepository.save(producto);
      inventarioService.crearInventario(producto.getId(),productoRequest.getStockMinimo());
      return ResponseEntity.status(HttpStatus.CREATED).body(new GenericResponse(HttpStatus.CREATED.value(), "Producto creado con éxito"));
    } catch (DataIntegrityViolationException e) {
      if (Objects.requireNonNull(e.getRootCause()).getMessage().toLowerCase().contains("codigo_barras") && e.getRootCause() != null) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                  .body(new GenericResponse(HttpStatus.BAD_REQUEST.value(), "El código de barras ya existe"));
        }
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
              .body(new GenericResponse(HttpStatus.BAD_REQUEST.value(), "Error de integridad en la base de datos"));
    }
  }

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

  @Override
  public ProductoResponse obtenerInfoWeb(String codigoBarras){
    String url = "https://go-upc.com/search?q="+codigoBarras;
    //Document doc = Jsoup.connect(url).get();
    return null;
  }
}
