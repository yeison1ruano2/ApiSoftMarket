package com.softmarket.apisoftmarket.mapper;

import com.softmarket.apisoftmarket.dto.ClienteResponse;
import com.softmarket.apisoftmarket.dto.DetalleVentaResponse;
import com.softmarket.apisoftmarket.dto.UsuarioResponse;
import com.softmarket.apisoftmarket.dto.VentaResponse;
import com.softmarket.apisoftmarket.entity.Cliente;
import com.softmarket.apisoftmarket.entity.DetalleVenta;
import com.softmarket.apisoftmarket.entity.Usuario;
import com.softmarket.apisoftmarket.entity.Venta;
import com.softmarket.apisoftmarket.services.ClienteService;
import com.softmarket.apisoftmarket.services.UsuarioService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service
public class VentaMapper {

  private final DetalleVentaMapper detalleVentaMapper;
  private final ClienteService clienteService;
  private final UsuarioService usuarioService;

  public VentaMapper(DetalleVentaMapper detalleVentaMapper, ClienteService clienteService, UsuarioService usuarioService) {
    this.detalleVentaMapper = detalleVentaMapper;
    this.usuarioService = usuarioService;
    this.clienteService = clienteService;
  }

  public Venta entityCreate(Venta venta, Cliente cliente, Usuario usuario, String metodoDePago, BigDecimal totalVenta, List<DetalleVenta> detalles) {
    venta.setCliente(cliente);
    venta.setUsuario(usuario);
    venta.setFecha(Instant.now().toEpochMilli());
    venta.setMetodoDePago(metodoDePago);
    venta.setCodigoFactura("FAC-"+System.currentTimeMillis());
    venta.setEstado(true);
    venta.setTotal(totalVenta);
    venta.setDetalles(detalles);
    return venta;
  }

  public VentaResponse getVentaFactura(Venta venta, List<DetalleVenta> detalleVentaList) {
    List<DetalleVentaResponse> detalleVentaResponseList = detalleVentaList
            .stream()
            .map(detalleVentaMapper::detalleVentaToDetalleVentaResponse)
            .toList();
    ClienteResponse clienteResponse =  clienteService.obtenerClienteId(venta.getCliente().getId());
    UsuarioResponse usuarioResponse = usuarioService.obetenerUsuarioId(venta.getUsuario().getId());
    return new VentaResponse(
            venta.getCodigoFactura(),
            clienteResponse,
            usuarioResponse,
            venta.getFecha(),
            venta.getTotal(),
            detalleVentaResponseList

    );


  }

  public List<VentaResponse> convertEntityToResponseList(List<Venta> ventaList) {
    return ventaList
            .stream()
            .map(venta -> {
              List<DetalleVentaResponse>detalleVentaResponseList = venta.getDetalles()
                      .stream()
                      .map(detalleVentaMapper::detalleVentaToDetalleVentaResponse)
                      .toList();
              ClienteResponse clienteResponse = clienteService.obtenerClienteId(venta.getCliente().getId());
              UsuarioResponse usuarioResponse = usuarioService.obetenerUsuarioId(venta.getUsuario().getId());
              return new VentaResponse(
                      venta.getCodigoFactura(),
                      clienteResponse,
                      usuarioResponse,
                      venta.getFecha(),
                      venta.getTotal(),
                      detalleVentaResponseList
              );
            })
            .toList();
  }
}
