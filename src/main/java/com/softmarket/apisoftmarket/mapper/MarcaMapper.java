package com.softmarket.apisoftmarket.mapper;

import com.softmarket.apisoftmarket.dto.MarcaRequest;
import com.softmarket.apisoftmarket.dto.MarcaResponse;
import com.softmarket.apisoftmarket.entity.Marca;
import org.springframework.stereotype.Service;

@Service
public class MarcaMapper {
  public Marca requestToEntityCreate(MarcaRequest marcaRequest) {
    return new Marca(Long.parseLong(marcaRequest.getId()), marcaRequest.getNombre());
  }

  public Marca requestToEntityUpdate(Marca marca, MarcaRequest marcaRequest) {
    marca.setNombre(marcaRequest.getNombre());
    return marca;
  }

  public MarcaResponse entityToResponse(Marca marca) {
    return new MarcaResponse(
            Long.toString(marca.getId()),
            marca.getNombre()
    );
  }
}
