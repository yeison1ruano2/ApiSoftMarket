package com.softmarket.apisoftmarket.mapper;

import com.softmarket.apisoftmarket.dto.CategoriaResponse;
import com.softmarket.apisoftmarket.dto.MarcaResponse;
import com.softmarket.apisoftmarket.dto.ProductoRequest;
import com.softmarket.apisoftmarket.dto.ProductoResponse;
import com.softmarket.apisoftmarket.entity.Producto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductoMapper {

  public Producto requestToEntityCreate(ProductoRequest productoRequest, BigDecimal iva){
    return new Producto(
            null,
            productoRequest.getNombre(),
            productoRequest.getCodigoBarras(),
            productoRequest.getMarca(),
            productoRequest.getCategoria(),
            productoRequest.getPrecioVenta(),
            productoRequest.getPrecioPorMayor(),
            productoRequest.getCantidadMinimaMayor(),
            iva
    );
  }

  public ProductoResponse entityToResponse(Producto producto,Integer stock) {
    return new ProductoResponse(
            producto.getNombre(),
            producto.getCodigoBarras(),
            producto.getPrecioVenta(),
            producto.getPrecioPorMayor(),
            producto.getCantidadMinimaMayor(),
            producto.getMarca(),
            producto.getCategoria(),
            stock,
            producto.getIva()
    );
  }

  public Producto requestToEntityUpdate(Producto producto, ProductoRequest productoRequest) {
    producto.setNombre(productoRequest.getNombre());
    producto.setCodigoBarras(productoRequest.getCodigoBarras());
    producto.setPrecioVenta(productoRequest.getPrecioVenta());
    producto.setPrecioPorMayor(productoRequest.getPrecioPorMayor());
    producto.setCantidadMinimaMayor(productoRequest.getCantidadMinimaMayor());
    return producto;
  }

  public ProductoResponse entityToResponseCreate(Producto producto, MarcaResponse marca, CategoriaResponse categoria) {
    return new ProductoResponse(
            producto.getNombre(),
            producto.getCodigoBarras(),
            marca.getNombre(),
            producto.getPrecioVenta(),
            categoria.getNombre()
    );
  }
}
