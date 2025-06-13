package com.softmarket.apisoftmarket.repository;

import com.softmarket.apisoftmarket.entity.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarcaRepository extends JpaRepository<Marca,Long> {
  @Query("SELECT m FROM Marca m WHERE LOWER(m.nombre) LIKE LOWER(CONCAT('%',:nombre,'%'))")
  List<Marca> findByNombreList(@Param("nombre") String nombre);

  @Query("SELECT m FROM Marca m WHERE LOWER(m.nombre) LIKE LOWER(CONCAT('%',:nombre,'%'))")
  Marca findByNombre(@Param("nombre") String nombre);

}
