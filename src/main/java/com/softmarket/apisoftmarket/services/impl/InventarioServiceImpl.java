package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.dto.GenericResponse;
import com.softmarket.apisoftmarket.dto.InventarioResponse;
import com.softmarket.apisoftmarket.entity.Inventario;
import com.softmarket.apisoftmarket.entity.MovimientoInventario;
import com.softmarket.apisoftmarket.entity.Producto;
import com.softmarket.apisoftmarket.exception.InventarioException;
import com.softmarket.apisoftmarket.exception.ProductoException;
import com.softmarket.apisoftmarket.exception.StockException;
import com.softmarket.apisoftmarket.mapper.InventarioMapper;
import com.softmarket.apisoftmarket.repository.InventarioRepository;
import com.softmarket.apisoftmarket.repository.MovimientoInventarioRepository;
import com.softmarket.apisoftmarket.repository.ProductoRepository;
import com.softmarket.apisoftmarket.services.InventarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class InventarioServiceImpl implements InventarioService {

  private static final String PRODUCTONOENCONTRADO = "Producto no encontrado";
  private static final String INVENTARIONOENCONTRADO = "Inventario no encontrado";

  private final InventarioRepository inventarioRepository;
  private final MovimientoInventarioRepository movimientoRepository;
  private final ProductoRepository productoRepository;
  private final InventarioMapper inventarioMapper;

  public InventarioServiceImpl(InventarioRepository inventarioRepository,  MovimientoInventarioRepository movimientoRepository, ProductoRepository productoRepository, InventarioMapper inventarioMapper) {
    this.inventarioRepository = inventarioRepository;
    this.movimientoRepository = movimientoRepository;
    this.productoRepository = productoRepository;
    this.inventarioMapper = inventarioMapper;
  }

  @Override
  public ResponseEntity<GenericResponse> ingresarStock(String codigoBarras, Integer cantidadNueva) {
    Producto producto = productoRepository.findByCodigoBarras(codigoBarras).orElseThrow(() -> new ProductoException(PRODUCTONOENCONTRADO) );
    Inventario inventario = inventarioRepository.findByProducto(producto).orElseThrow(()-> new InventarioException(INVENTARIONOENCONTRADO));
    inventario.setCantidadActual(inventario.getCantidadActual() + cantidadNueva);
    inventario.setUltimaActualizacion(Instant.now().toEpochMilli());
    inventarioRepository.save(inventario);

    MovimientoInventario movimientoInventario = new MovimientoInventario(null,inventario, "ENTRADA",cantidadNueva);
    movimientoRepository.save(movimientoInventario);
    return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse(HttpStatus.OK.getReasonPhrase(),"Stock ingresado con éxito"));
  }

  @Override
  public ResponseEntity<GenericResponse> retirarStock(String codigoBarras, Integer cantidad) throws RuntimeException {
    Producto producto = productoRepository.findByCodigoBarras(codigoBarras).orElseThrow(() -> new ProductoException(PRODUCTONOENCONTRADO));
    Inventario inventario = inventarioRepository.findByProducto(producto).orElseThrow(()-> new InventarioException(INVENTARIONOENCONTRADO));
    if(inventario.getCantidadActual()<cantidad){
      throw new StockException("Stock insuficiente");
    }

    inventario.setCantidadActual(inventario.getCantidadActual() - cantidad);
    inventario.setUltimaActualizacion(Instant.now().toEpochMilli());
    inventarioRepository.save(inventario);

    MovimientoInventario movimientoInventario = new MovimientoInventario(
            null,
            inventario,
            "SALIDA",
            cantidad
    );
    movimientoRepository.save(movimientoInventario);
    return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse(HttpStatus.OK.getReasonPhrase(),"Stock retirado con éxito"));
  }

  @Override
  public ResponseEntity<InventarioResponse> obtenerInventario(String codigoBarras) {
    return productoRepository.findByCodigoBarras(codigoBarras)
            .map(producto -> {
              Inventario inventario = inventarioRepository.findByProducto(producto).orElseThrow(()-> new InventarioException(INVENTARIONOENCONTRADO));
              InventarioResponse inventarioResponse = inventarioMapper.entityToResponse(inventario,producto);
              return ResponseEntity.status(HttpStatus.OK).body(inventarioResponse);
            })
            .orElseThrow(() -> new ProductoException(PRODUCTONOENCONTRADO));
  }

  @Override
  public void crearInventario(Long productoId, Integer stockMinimo) {
    Producto producto = productoRepository.findById(productoId).orElseThrow(() -> new ProductoException(PRODUCTONOENCONTRADO));
    Inventario inventario = new Inventario(producto,0,stockMinimo);
    inventarioRepository.save(inventario);
  }

  @Override
  public Boolean validarStock(Long productoId,Integer cantidad) {
    Inventario inventario = inventarioRepository.findByInventarioProductoId(productoId);
    return cantidad <= inventario.getCantidadActual();
  }

  @Override
  public Integer obtenerStock(String codigoBarras) {
    Optional<Producto> productoOptional = productoRepository.findByCodigoBarras(codigoBarras);
    if(productoOptional.isEmpty()){
      return 0;
    }
    Inventario inventario = inventarioRepository.findByInventarioProductoId(productoOptional.get().getId());
    return inventario.getCantidadActual();
  }

}
