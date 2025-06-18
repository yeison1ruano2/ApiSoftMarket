package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.entity.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

  private final String SECRET_KEY = "12345678901234567890123456789012";
  private final long EXPIRATION = 1000 * 60 * 60 * 24;

  public String generateToken(Usuario usuario){
    return Jwts.builder()
            .setSubject(usuario.getUsername())
            .claim("roles",usuario.getRoles())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
            .signWith(getKey())
            .compact();
  }

  public String extractUsername(String token){
    return Jwts.parserBuilder()
            .setSigningKey(getKey())
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
  }

  private Key getKey(){
    return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
  }
}
