package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.dto.UsuarioResponse;
import com.softmarket.apisoftmarket.entity.Usuario;
import com.softmarket.apisoftmarket.repository.UsuarioRepository;
import com.softmarket.apisoftmarket.services.UsuarioService;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

  private final UsuarioRepository usuarioRepository;

  public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }

  @Override
  public UsuarioResponse obetenerUsuarioId(Long id) {
    Usuario usuario =  usuarioRepository.findById(id).get();
    return new UsuarioResponse(
          usuario.getNombre(),
          usuario.getApellido(),
          usuario.getIdentificacion()
    );
  }
}
