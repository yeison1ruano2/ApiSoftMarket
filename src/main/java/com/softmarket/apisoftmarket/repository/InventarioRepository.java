package com.softmarket.apisoftmarket.repository;

import com.softmarket.apisoftmarket.entity.Inventario;
import com.softmarket.apisoftmarket.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario,Long> {
  Optional<Inventario> findByProducto(Producto producto);

  @Query("SELECT i FROM Inventario i WHERE i.producto.id = :productoId")
  Inventario findByInventarioProductoId(Long productoId);

}
