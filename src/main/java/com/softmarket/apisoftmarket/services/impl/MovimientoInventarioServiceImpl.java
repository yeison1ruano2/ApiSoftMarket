package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.entity.Inventario;
import com.softmarket.apisoftmarket.entity.MovimientoInventario;
import com.softmarket.apisoftmarket.repository.MovimientoInventarioRepository;
import com.softmarket.apisoftmarket.services.MovimientoInventarioService;
import org.springframework.stereotype.Service;

@Service
public class MovimientoInventarioServiceImpl implements MovimientoInventarioService {

  private final MovimientoInventarioRepository movimientoInventarioRepository;

  public MovimientoInventarioServiceImpl(MovimientoInventarioRepository movimientoInventarioRepository) {
    this.movimientoInventarioRepository = movimientoInventarioRepository;
  }

  @Override
  public void crearEntrada(Inventario inventario, Integer cantidad) {
    MovimientoInventario movimientoInventario = new MovimientoInventario(
            null,inventario,"ENTRADA",
            cantidad
    );
    movimientoInventarioRepository.save(movimientoInventario);
  }

  @Override
  public void crearSalida(Inventario inventario, Integer cantidad) {
    MovimientoInventario movimientoInventario = new MovimientoInventario(
            null,inventario,"SALIDA",
            cantidad
    );
    movimientoInventarioRepository.save(movimientoInventario);
  }
}
