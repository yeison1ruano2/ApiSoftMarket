package com.softmarket.apisoftmarket.mapper;

import com.softmarket.apisoftmarket.entity.Token;
import com.softmarket.apisoftmarket.entity.Usuario;
import org.springframework.stereotype.Service;

@Service
public class TokenMapper {
  public Token tokenCreate(String token, Usuario usuario) {
    return new Token(
            null,
            token,
            false,
            false,
            usuario
    );
  }
}
