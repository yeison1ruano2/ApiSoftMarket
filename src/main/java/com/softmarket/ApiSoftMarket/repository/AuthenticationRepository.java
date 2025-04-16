package com.softmarket.ApiSoftMarket.repository;

import com.softmarket.ApiSoftMarket.entity.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AuthenticationRepository extends JpaRepository<Authentication,Long> {

  @Query("SELECT a FROM Authentication a ORDER BY a.id ASC")
  Optional<Authentication> findFirstAuthentication();

  Optional<Authentication> findByUsername(String username);
}
