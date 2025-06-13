package com.softmarket.apisoftmarket.repository;

import com.softmarket.apisoftmarket.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {
  @Query("SELECT p FROM Producto p WHERE p.codigoBarras = :codigoBarras")
  Optional<Producto> findByCodigoBarras(@Param("codigoBarras") String codigoBarras);

  @Query("SELECT p FROM Producto p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%',:nombre,'%'))")
  List<Producto> findByNombre(@Param("nombre") String nombre);
}
