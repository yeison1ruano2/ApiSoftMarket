package com.softmarket.apisoftmarket.mapper;

import com.softmarket.apisoftmarket.dto.CategoriaRequest;
import com.softmarket.apisoftmarket.dto.CategoriaResponse;
import com.softmarket.apisoftmarket.entity.Categoria;
import org.springframework.stereotype.Service;

@Service
public class CategoriaMapper {


  public Categoria requestToEntityCreate(CategoriaRequest categoriaRequest) {
    return new Categoria(Long.parseLong(categoriaRequest.getId()) ,
            categoriaRequest.getNombre()
    );
  }

  public Categoria requestToEntityUpdate(Categoria categoria, CategoriaRequest categoriaRequest) {
    categoria.setNombre(categoriaRequest.getNombre());
    return categoria;
  }

  public CategoriaResponse entityToResponse(Categoria categoria) {
    return new CategoriaResponse(
            categoria.getId().toString(),
            categoria.getNombre()
    );
  }
}
