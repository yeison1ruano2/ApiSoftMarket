package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.entity.Token;
import com.softmarket.apisoftmarket.entity.Usuario;
import com.softmarket.apisoftmarket.exception.TokenException;
import com.softmarket.apisoftmarket.repository.TokenRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

  private final String SECRETKEY = "12345678901234567890123456789012";
  private final long EXPIRATION = 1000 * 60 * 5;
  private final TokenRepository tokenRepository;

  public JwtService(TokenRepository tokenRepository) {
    this.tokenRepository = tokenRepository;
  }

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
    try{
      return Jwts.parserBuilder()
              .setSigningKey(getKey())
              .build()
              .parseClaimsJws(token)
              .getBody()
              .getSubject();
    }catch(ExpiredJwtException e){
      Token tokenDb = tokenRepository.findByToken(token).orElseThrow(()-> new TokenException("Token no encontrado"));
      return generateToken(tokenDb.getUsuario());
    }
  }

  private Key getKey(){
    return Keys.hmacShaKeyFor(SECRETKEY.getBytes());
  }

  public boolean isValid(String token, UserDetails userDetails){
    final String username = extractUsername(token);
    return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
  }

  public boolean isTokenExpired(String token){
    Date expiration = Jwts.parserBuilder()
            .setSigningKey(SECRETKEY.getBytes())
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getExpiration();
    return expiration.before(new Date());
  }
}
