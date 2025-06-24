package com.softmarket.apisoftmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.softmarket.apisoftmarket.entity.Authentication;

import java.util.Optional;
@Repository
public interface AuthenticationRepository extends JpaRepository<Authentication,Long> {

  Optional<Authentication> findByNombre(String nombre);
}
