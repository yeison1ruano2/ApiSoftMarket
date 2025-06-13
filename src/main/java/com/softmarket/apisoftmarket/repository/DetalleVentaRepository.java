package com.softmarket.apisoftmarket.repository;

import com.softmarket.apisoftmarket.entity.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta,Long> {

  @Query("SELECT dv FROM DetalleVenta dv WHERE dv.venta.id = :ventaId")
  List<DetalleVenta> findByVentaId(@Param("ventaId") Long ventaId);
}
