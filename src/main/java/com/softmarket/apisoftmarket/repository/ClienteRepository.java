package com.softmarket.apisoftmarket.repository;

import com.softmarket.apisoftmarket.dto.ClienteResponse;
import com.softmarket.apisoftmarket.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
  @Query("SELECT c FROM Cliente c WHERE " +
          "LOWER(c.nombre) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
          "LOWER(c.apellido) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
          "LOWER(c.identificacion) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
          "LOWER(c.telefono) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
          "LOWER(c.direccion) LIKE LOWER(CONCAT('%', :termino, '%'))")
  List<Cliente> buscarPorTermino(String termino);
}
