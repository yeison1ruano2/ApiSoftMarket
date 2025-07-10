package com.softmarket.apisoftmarket.mapper;

import com.softmarket.apisoftmarket.dto.InventarioResponse;
import com.softmarket.apisoftmarket.dto.ProductoResponse;
import com.softmarket.apisoftmarket.entity.Inventario;
import com.softmarket.apisoftmarket.entity.Producto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class InventarioMapper {
  public InventarioResponse entityToResponseingresaStock() {
    return new InventarioResponse(
            HttpStatus.OK.getReasonPhrase(),
            "Stock ingresado con éxito"

    );
  }

  public InventarioResponse entityToResponse(Inventario inventario, Producto producto) {
    return new InventarioResponse(
            producto.getNombre(),
            producto.getCodigoBarras(),
            inventario.getCantidadActual(),
            HttpStatus.OK.getReasonPhrase(),
            ""
    );
  }

  public ProductoResponse entityToProductoResponse(Inventario inventario) {
    return new ProductoResponse(
            inventario.getProducto().getNombre(),
            inventario.getProducto().getCodigoBarras(),
            inventario.getProducto().getPrecioVenta(),
            inventario.getProducto().getPrecioPorMayor(),
            inventario.getProducto().getCantidadMinimaMayor(),
            inventario.getProducto().getMarca(),
            inventario.getProducto().getCategoria(),
            inventario.getCantidadActual()
    );
  }
}
