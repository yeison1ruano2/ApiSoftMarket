package com.softmarket.apisoftmarket.repository;

import com.softmarket.apisoftmarket.entity.Token;
import com.softmarket.apisoftmarket.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {

  Optional<Token> findByUsuario(Usuario usuario);

  Optional<Token> findByToken(String token);
}
