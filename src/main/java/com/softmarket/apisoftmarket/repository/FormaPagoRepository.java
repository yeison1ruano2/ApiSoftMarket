package com.softmarket.apisoftmarket.repository;

import com.softmarket.apisoftmarket.entity.CodigosFormasPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FormaPagoRepository extends JpaRepository<CodigosFormasPago,Long> {
  @Query("SELECT fp FROM CodigosFormasPago fp WHERE fp.codigo = :codigo")
  Optional<CodigosFormasPago> findByCodigo(@Param("codigo") String codigo);
}
