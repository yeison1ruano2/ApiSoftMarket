package com.softmarket.apisoftmarket.repository;

import com.softmarket.apisoftmarket.entity.DataSheetProductosIva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IvaDatSheetRepository extends JpaRepository<DataSheetProductosIva,String> {
  @Query(value = "SELECT * FROM productos_iva p WHERE LOWER(:nameProducto) LIKE CONCAT('%', LOWER(p.descripcion), '%') LIMIT 1", nativeQuery = true)
  DataSheetProductosIva buscarCoincidenciaCadena(@Param("nameProducto") String nameProducto);
}
