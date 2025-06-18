package com.softmarket.apisoftmarket.repository;

import com.softmarket.apisoftmarket.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta,Long> {
  @Query("SELECT v FROM Venta v WHERE v.codigoFactura = :codigoFactura")
  Venta findBycodigoFactura(@Param("codigoFactura") String codigoFactura);

  @Query("SELECT DISTINCT v FROM Venta v LEFT JOIN FETCH v.detalles")
  List<Venta> findAllVentasWithDetalles();
}
