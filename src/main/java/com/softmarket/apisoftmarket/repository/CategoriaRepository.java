package com.softmarket.apisoftmarket.repository;

import com.softmarket.apisoftmarket.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
  /*@Query("SELECT c FROM Categoria c WHERE p.id =:id")
  Optional<Categoria> findById(@Param("id") Long id);*/

  @Query("SELECT c FROM Categoria c WHERE LOWER(c.nombre) LIKE LOWER(CONCAT('%',:nombre,'%'))")
  List<Categoria> findByNombreList(@Param("nombre") String nombre);

  @Query("SELECT c FROM Categoria c WHERE LOWER(c.nombre) LIKE LOWER(CONCAT('%',:nombre,'%'))")
  Categoria findByNombre(@Param("nombre") String nombre);
}
