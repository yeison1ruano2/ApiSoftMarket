package com.softmarket.apisoftmarket.mapper;

import com.softmarket.apisoftmarket.dto.DetalleVentaResponse;
import com.softmarket.apisoftmarket.entity.DetalleVenta;
import com.softmarket.apisoftmarket.entity.Producto;
import com.softmarket.apisoftmarket.entity.Venta;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DetalleVentaMapper {
  public static DetalleVenta detalleCreate(Producto producto, int cantidad, BigDecimal precioVenta, BigDecimal subtotal, Venta venta) {
    return new DetalleVenta(
            null,
            venta,
            producto,
            cantidad,
            precioVenta,
            subtotal
    );
  }

  public DetalleVentaResponse detalleVentaToDetalleVentaResponse(DetalleVenta detalleVenta) {
    return new DetalleVentaResponse(
            detalleVenta.getProducto().getNombre(),
            detalleVenta.getCantidad(),
            detalleVenta.getPrecioUnitario(),
            detalleVenta.getSubtotal()
    );
  }
}
