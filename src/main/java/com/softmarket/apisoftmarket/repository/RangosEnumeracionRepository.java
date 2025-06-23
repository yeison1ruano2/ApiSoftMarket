package com.softmarket.apisoftmarket.repository;

import com.softmarket.apisoftmarket.entity.RangosEnumeracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RangosEnumeracionRepository extends JpaRepository<RangosEnumeracion,Long> {
  @Query("SELECT re.id FROM RangosEnumeracion re WHERE re.document = 'Factura de Venta'")
  Long findByFacturaVenta();
}
