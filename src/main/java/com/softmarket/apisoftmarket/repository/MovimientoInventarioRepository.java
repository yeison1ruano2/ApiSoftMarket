package com.softmarket.apisoftmarket.repository;

import com.softmarket.apisoftmarket.entity.Inventario;
import com.softmarket.apisoftmarket.entity.MovimientoInventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario,Long> {
  List<MovimientoInventario> findByInventario(Inventario inventario);
}
