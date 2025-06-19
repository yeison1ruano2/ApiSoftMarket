package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.dto.DetalleVentaRequest;
import com.softmarket.apisoftmarket.dto.GenericResponse;
import com.softmarket.apisoftmarket.dto.VentaRequest;
import com.softmarket.apisoftmarket.dto.VentaResponse;
import com.softmarket.apisoftmarket.entity.*;
import com.softmarket.apisoftmarket.exception.ClienteException;
import com.softmarket.apisoftmarket.exception.ProductoException;
import com.softmarket.apisoftmarket.exception.UsuarioException;
import com.softmarket.apisoftmarket.mapper.DetalleVentaMapper;
import com.softmarket.apisoftmarket.mapper.VentaMapper;
import com.softmarket.apisoftmarket.repository.*;
import com.softmarket.apisoftmarket.services.InventarioService;
import com.softmarket.apisoftmarket.services.VentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class VentaServiceImpl implements VentaService {

  private final ClienteRepository clienteRepository;
  private final UsuarioRepository usuarioRepository;
  private final ProductoRepository productoRepository;
  private final VentaRepository ventaRepository;
  private final InventarioService inventarioService;
  private final VentaMapper ventaMapper;
  private final DetalleVentaRepository detalleVentaRepository;
  public VentaServiceImpl(ClienteRepository clienteRepository, UsuarioRepository usuarioRepository, ProductoRepository productoRepository, VentaRepository ventaRepository, InventarioService inventarioService, VentaMapper ventaMapper, DetalleVentaRepository detalleVentaRepository) {
    this.clienteRepository = clienteRepository;
    this.usuarioRepository = usuarioRepository;
    this.productoRepository = productoRepository;
    this.ventaRepository = ventaRepository;
    this.inventarioService = inventarioService;
    this.ventaMapper = ventaMapper;
    this.detalleVentaRepository = detalleVentaRepository;
  }

  @Override
  public GenericResponse registrarVenta(VentaRequest ventaRequest) {
      Cliente cliente = clienteRepository.findById(Long.parseLong(ventaRequest.getClienteId()))
              .orElseThrow(()->new ClienteException("Cliente no encontrado"));
      Usuario usuario = usuarioRepository.findById(Long.parseLong(ventaRequest.getUsuarioId()))
              .orElseThrow(()->new UsuarioException("Usuario no encontrado"));
      Venta venta = new Venta();
      AtomicReference<BigDecimal> totalVenta = new AtomicReference<>(BigDecimal.ZERO);
      List<DetalleVenta> detalles = new ArrayList<>();
      for(DetalleVentaRequest d: ventaRequest.getDetalles()){
        DetalleVenta detalle = procesarDetalle(d,venta,totalVenta);
        detalles.add(detalle);
      }
      Venta ventaCreate = ventaMapper.entityCreate(venta,cliente,usuario,ventaRequest.getMetodoDePago(),totalVenta.get(),detalles);
      ventaRepository.save(ventaCreate);
      return new GenericResponse(HttpStatus.OK.value(),"Venta realizada con éxito");
  }

  @Override
  public ResponseEntity<VentaResponse> listarVentasFactura(String codigoFactura) {
    Venta venta =  ventaRepository.findBycodigoFactura(codigoFactura);
    List<DetalleVenta> detalleVentaList = detalleVentaRepository.findByVentaId(venta.getId());
    VentaResponse ventaResponse = ventaMapper.getVentaFactura(venta,detalleVentaList);
    return ResponseEntity.status(HttpStatus.OK).body(ventaResponse);
  }

  @Override
  public ResponseEntity<List<VentaResponse>> listarVentas() {
    List<Venta> ventaList = ventaRepository.findAllVentasWithDetalles();
    List<VentaResponse> ventaResponseList = ventaMapper.convertEntityToResponseList(ventaList);
    return ResponseEntity.status(HttpStatus.OK).body(ventaResponseList);
  }

  private DetalleVenta procesarDetalle(DetalleVentaRequest detalleRequest, Venta venta, AtomicReference<BigDecimal> totalVenta) {
    Long productoId = Long.parseLong(detalleRequest.getProductoId());
    int cantidad = detalleRequest.getCantidad();

    Producto producto = productoRepository.findById(productoId)
            .orElseThrow(()->new ProductoException("Producto no encontrado"));
    BigDecimal precioUnitario = producto.getPrecioVenta();
    BigDecimal subtotal = precioUnitario.multiply(BigDecimal.valueOf(cantidad));

    totalVenta.updateAndGet(current -> current.add(subtotal));
    inventarioService.retirarStock(producto.getCodigoBarras(), cantidad);
    return DetalleVentaMapper.detalleCreate(producto,cantidad,precioUnitario,subtotal,venta);
  }
}
