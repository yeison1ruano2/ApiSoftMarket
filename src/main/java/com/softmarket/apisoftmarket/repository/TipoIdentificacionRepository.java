package com.softmarket.apisoftmarket.repository;

import com.softmarket.apisoftmarket.entity.TipoIdentificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoIdentificacionRepository extends JpaRepository<TipoIdentificacion,Long> {

  @Query("SELECT ti FROM TipoIdentificacion ti WHERE LOWER(ti.nombre) LIKE LOWER(CONCAT('%',:tipoIdentificacion,'%'))")
  TipoIdentificacion findByTipoIdentificacionNombre(@Param("tipoIdentificacion") String tipoIdentificacion);
}
