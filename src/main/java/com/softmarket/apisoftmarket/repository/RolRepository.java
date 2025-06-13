package com.softmarket.apisoftmarket.repository;

import com.softmarket.apisoftmarket.dto.Role;
import com.softmarket.apisoftmarket.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol,Long> {
  Optional<Rol> findByNombre(Role nombre);
}
