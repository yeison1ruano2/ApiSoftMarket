package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.dto.GenericResponse;
import com.softmarket.apisoftmarket.dto.InventarioRequest;
import com.softmarket.apisoftmarket.dto.InventarioResponse;
import com.softmarket.apisoftmarket.entity.Inventario;
import com.softmarket.apisoftmarket.entity.MovimientoInventario;
import com.softmarket.apisoftmarket.entity.Producto;
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
  private final InventarioRepository inventarioRepository;

  private final MovimientoInventarioRepository movimientoRepository;

  private final ProductoRepository productoRepository;

  private final InventarioMapper inventarioMapper;

  public InventarioServiceImpl(InventarioRepository inventarioRepository, MovimientoInventarioRepository movimientoRepository, ProductoRepository productoRepository, InventarioMapper inventarioMapper) {
    this.inventarioRepository = inventarioRepository;
    this.movimientoRepository = movimientoRepository;
    this.productoRepository = productoRepository;
    this.inventarioMapper = inventarioMapper;
  }

  @Override
  public ResponseEntity<GenericResponse> ingresarStock(String codigoBarras, Integer cantidadNueva) {
    Producto producto = productoRepository.findByCodigoBarras(codigoBarras).orElse(null);
    if(producto == null){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponse(HttpStatus.NOT_FOUND.getReasonPhrase(),PRODUCTONOENCONTRADO));
    }
    Optional<Inventario> inventarioOptional = inventarioRepository.findByProducto(producto);
    if(inventarioOptional.isEmpty()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponse(HttpStatus.NOT_FOUND.getReasonPhrase(),"Inventario no encontrado"));
    }
    Inventario inventario = inventarioOptional.get();
    inventario.setCantidadActual(inventario.getCantidadActual() + cantidadNueva);
    inventario.setUltimaActualizacion(Instant.now().toEpochMilli());
    inventarioRepository.save(inventario);

    MovimientoInventario movimientoInventario = new MovimientoInventario(
            null,
            inventario,
            "ENTRADA",
            cantidadNueva
    );
    movimientoRepository.save(movimientoInventario);
    return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse(HttpStatus.OK.getReasonPhrase(),"Stock ingresado con éxito"));
  }

  @Override
  public ResponseEntity<GenericResponse> retirarStock(String codigoBarras, int cantidad) throws RuntimeException {
    Producto producto = productoRepository.findByCodigoBarras(codigoBarras)
            .orElseThrow(()-> new RuntimeException(PRODUCTONOENCONTRADO));
    Inventario inventario = inventarioRepository.findByProducto(producto)
            .orElseThrow(()-> new RuntimeException("Inventario no encontrado"));
    if(inventario.getCantidadActual()<cantidad){
      throw new RuntimeException("Stock insuficiente");
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
              Optional<Inventario> inventarioOptional = inventarioRepository.findByProducto(producto);
              if(inventarioOptional.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new InventarioResponse(HttpStatus.NOT_FOUND.getReasonPhrase(),"No existe inventario para ese producto"));
              }
              InventarioResponse inventarioResponse = inventarioMapper.entityToResponse(inventarioOptional.get(),producto);
              return ResponseEntity.status(HttpStatus.OK).body(inventarioResponse);
            })
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new InventarioResponse(HttpStatus.NOT_FOUND.getReasonPhrase(),PRODUCTONOENCONTRADO)));
  }

  @Override
  public InventarioResponse crearStock(Long productoId, Integer stockMinimo) {
    InventarioRequest inventarioRequest = new InventarioRequest(stockMinimo, 0, "");
    Producto producto = productoRepository.findById(productoId).orElse(null);
    if(producto == null){
      return new InventarioResponse(HttpStatus.NOT_FOUND.getReasonPhrase(),PRODUCTONOENCONTRADO);
    }
    Inventario inventario = inventarioRepository.findByProducto(producto)
            .orElseGet(()->
                    new Inventario(producto, 0, inventarioRequest.getStockMinimo()));
    inventario.setCantidadActual(inventario.getCantidadActual() + inventarioRequest.getCantidad());
    inventario.setUltimaActualizacion(Instant.now().toEpochMilli());
    inventarioRepository.save(inventario);
    return inventarioMapper.entityToResponseingresaStock();
  }


}
