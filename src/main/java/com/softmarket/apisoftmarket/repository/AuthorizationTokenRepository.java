package com.softmarket.apisoftmarket.repository;

import com.softmarket.apisoftmarket.entity.AuthorizationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorizationTokenRepository extends JpaRepository<AuthorizationToken,Long> {
  @Query("SELECT at FROM AuthorizationToken at WHERE at.authId.id = :authId")
  Optional<AuthorizationToken> findByAuthId(@Param("authId") Long authId);

  Optional<AuthorizationToken> findByAuthIdId(Long authId);
}
