package com.softmarket.apisoftmarket.repository;

import com.softmarket.apisoftmarket.entity.MetodoDePago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MetodoDePagoRepository extends JpaRepository<MetodoDePago,Long> {
  @Query("SELECT mp FROM MetodoDePago mp WHERE " +
          "LOWER(mp.codigo) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
          "LOWER(mp.nombre) LIKE LOWER(CONCAT('%', :termino, '%'))")
  Optional<MetodoDePago> buscarPorTermino(String termino);
}
