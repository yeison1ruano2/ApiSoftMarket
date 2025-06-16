package com.softmarket.apisoftmarket.repository;

import com.softmarket.apisoftmarket.entity.TipoCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoClienteRepository extends JpaRepository<TipoCliente,Long> {

  @Query("SELECT tp FROM TipoCliente tp WHERE LOWER(tp.nombre) LIKE LOWER(CONCAT('%',:tipoCliente,'%'))")
  TipoCliente findByTipoClienteNombre(@Param("tipoCliente") String tipoCliente);
}
