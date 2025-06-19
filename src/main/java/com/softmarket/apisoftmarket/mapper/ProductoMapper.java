package com.softmarket.apisoftmarket.mapper;

import com.softmarket.apisoftmarket.dto.CategoriaResponse;
import com.softmarket.apisoftmarket.dto.MarcaResponse;
import com.softmarket.apisoftmarket.dto.ProductoRequest;
import com.softmarket.apisoftmarket.dto.ProductoResponse;
import com.softmarket.apisoftmarket.entity.Categoria;
import com.softmarket.apisoftmarket.entity.Marca;
import com.softmarket.apisoftmarket.entity.Producto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ProductoMapper {

  public Producto requestToEntityCreate(ProductoRequest productoRequest, Marca marca, Categoria categoria){
    return new Producto(
            null,
            productoRequest.getNombre(),
            productoRequest.getCodigoBarras(),
            marca,
            categoria,
            productoRequest.getPrecioVenta(),
            productoRequest.getPrecioPorMayor(),
            productoRequest.getCantidadMinimaMayor()
    );
  }

  public ProductoResponse entityToResponse(Producto producto) {
    return new ProductoResponse(
            producto.getNombre(),
            producto.getCodigoBarras(),
            producto.getMarca().getNombre(),
            producto.getPrecioVenta(),
            producto.getCategoria().getNombre()
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
