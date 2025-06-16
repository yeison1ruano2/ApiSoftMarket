package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.entity.TipoCliente;
import com.softmarket.apisoftmarket.repository.TipoClienteRepository;
import com.softmarket.apisoftmarket.services.TipoClienteService;
import org.springframework.stereotype.Service;

@Service
public class TipoClienteServiceImpl implements TipoClienteService {

  private final TipoClienteRepository tipoClienteRepository;

  public TipoClienteServiceImpl(TipoClienteRepository tipoClienteRepository) {
    this.tipoClienteRepository = tipoClienteRepository;
  }

  @Override
  public TipoCliente obtenerTipoClienteNombre(String tipoCliente) {
    return tipoClienteRepository.findByTipoClienteNombre(tipoCliente);
  }
}
