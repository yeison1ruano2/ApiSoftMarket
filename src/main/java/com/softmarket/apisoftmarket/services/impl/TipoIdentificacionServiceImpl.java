package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.entity.TipoIdentificacion;
import com.softmarket.apisoftmarket.repository.TipoIdentificacionRepository;
import com.softmarket.apisoftmarket.services.TipoIdentificacionService;
import org.springframework.stereotype.Service;

@Service
public class TipoIdentificacionServiceImpl implements TipoIdentificacionService {

  private final TipoIdentificacionRepository tipoIdentificacionRepository;

  public TipoIdentificacionServiceImpl(TipoIdentificacionRepository tipoIdentificacionRepository) {
    this.tipoIdentificacionRepository = tipoIdentificacionRepository;
  }

  @Override
  public TipoIdentificacion obtenerTipoIdentificacionNombre(String tipoIdentificacion) {
    return tipoIdentificacionRepository.findByTipoIdentificacionNombre(tipoIdentificacion);
  }
}
