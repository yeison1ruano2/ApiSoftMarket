package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.entity.Token;
import com.softmarket.apisoftmarket.entity.Usuario;
import com.softmarket.apisoftmarket.repository.TokenRepository;
import com.softmarket.apisoftmarket.services.TokenService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenServiceImpl implements TokenService {

  private final TokenRepository tokenRepository;

  public TokenServiceImpl(TokenRepository tokenRepository) {
    this.tokenRepository = tokenRepository;
  }

  @Override
  public Optional<Token> obtenerUsuario(Usuario usuario) {
    return tokenRepository.findByUsuario(usuario);
  }

  @Override
  public void guardarToken(Token token) {
    tokenRepository.save(token);
  }
}
