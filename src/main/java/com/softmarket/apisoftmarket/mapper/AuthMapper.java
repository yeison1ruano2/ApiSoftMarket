package com.softmarket.apisoftmarket.mapper;

import com.softmarket.apisoftmarket.dto.UserRequest;
import com.softmarket.apisoftmarket.entity.TipoIdentificacion;
import com.softmarket.apisoftmarket.entity.Usuario;
import com.softmarket.apisoftmarket.exception.UsuarioException;
import com.softmarket.apisoftmarket.repository.UserRepository;
import com.softmarket.apisoftmarket.services.TipoIdentificacionService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthMapper {

  private final PasswordEncoder passwordEncoder;
  private final TipoIdentificacionService tipoIdentificacionService;
  private final UserRepository userRepository;

  public AuthMapper(PasswordEncoder passwordEncoder, TipoIdentificacionService tipoIdentificacionService, UserRepository userRepository) {
    this.passwordEncoder = passwordEncoder;
    this.tipoIdentificacionService = tipoIdentificacionService;
    this.userRepository = userRepository;
  }

  public Usuario requestToEntityCreate(UserRequest userRequest) {
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
