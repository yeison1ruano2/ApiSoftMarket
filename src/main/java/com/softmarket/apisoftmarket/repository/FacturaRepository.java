package com.softmarket.apisoftmarket.repository;

import com.softmarket.apisoftmarket.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FacturaRepository extends JpaRepository<Factura,Long> {
  @Query("SELECT f FROM Factura f WHERE f.reference_code = :referenceCode")
  Optional<Factura> findByReferenceCode(@Param("referenceCode") String referenceCode);
}
