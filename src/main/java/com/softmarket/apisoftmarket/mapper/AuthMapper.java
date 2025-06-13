package com.softmarket.apisoftmarket.mapper;

import com.softmarket.apisoftmarket.dto.UserRequest;
import com.softmarket.apisoftmarket.entity.Usuario;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthMapper {

  private final PasswordEncoder passwordEncoder;

  public AuthMapper(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  public Usuario requestToEntity(UserRequest userRequest) {
    String encodePassword = passwordEncoder.encode(userRequest.getPassword());
    return new Usuario(
            userRequest.getNombre(),
            userRequest.getApellido(),
            userRequest.getIdentificacion(),
            userRequest.getCorreo(),
            userRequest.getTelefono(),
            userRequest.getUsername(),
            encodePassword,
            userRequest.getRole(),
            true
    );
  }
}
