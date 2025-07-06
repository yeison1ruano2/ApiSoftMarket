package com.softmarket.apisoftmarket.services;

import com.softmarket.apisoftmarket.entity.Token;
import com.softmarket.apisoftmarket.entity.Usuario;

import java.util.Optional;

public interface TokenService {
  Optional<Token> obtenerUsuario(Usuario usuario);
  void guardarToken(Token token);
}
