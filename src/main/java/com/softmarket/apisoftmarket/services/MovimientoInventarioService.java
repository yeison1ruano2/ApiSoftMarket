package com.softmarket.apisoftmarket.services;

import com.softmarket.apisoftmarket.entity.Inventario;

public interface MovimientoInventarioService {

  void crearEntrada(Inventario inventario, Integer cantidad);
  void crearSalida(Inventario inventario, Integer cantidad);
}
