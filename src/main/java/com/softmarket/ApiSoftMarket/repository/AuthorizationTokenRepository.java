package com.softmarket.ApiSoftMarket.repository;

import com.softmarket.ApiSoftMarket.entity.AuthorizationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorizationTokenRepository extends JpaRepository<AuthorizationToken,Long> {

  @Query("SELECT at FROM AuthorizationToken at ORDER BY at.id ASC")
  Optional<AuthorizationToken> findFirstAuthentication();
}
