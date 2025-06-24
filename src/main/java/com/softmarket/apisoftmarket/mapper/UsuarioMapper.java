package com.softmarket.apisoftmarket.mapper;

import com.softmarket.apisoftmarket.dto.UsuarioRequest;
import com.softmarket.apisoftmarket.entity.TipoIdentificacion;
import com.softmarket.apisoftmarket.entity.Usuario;
import com.softmarket.apisoftmarket.services.TipoIdentificacionService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioMapper {

  private final TipoIdentificacionService tipoIdentificacionService;
  private final PasswordEncoder passwordEncoder;

  public UsuarioMapper(TipoIdentificacionService tipoIdentificacionService, PasswordEncoder passwordEncoder) {
    this.tipoIdentificacionService = tipoIdentificacionService;
    this.passwordEncoder = passwordEncoder;
  }

  public Usuario requestToEntityCreate(UsuarioRequest userRequest) {
    TipoIdentificacion tipoIdentificacion = tipoIdentificacionService.obtenerTipoIdentificacionNombre(userRequest.getTipoIdentificacion());
    String encodePassword = passwordEncoder.encode(userRequest.getPassword());
    return new Usuario(
            userRequest.getNombre(),
            userRequest.getApellido(),
            tipoIdentificacion,
            userRequest.getIdentificacion(),
            userRequest.getCorreo(),
            userRequest.getTelefono(),
            true,
            userRequest.getUsername(),
            encodePassword,
            userRequest.getRole()
    );
  }
}
