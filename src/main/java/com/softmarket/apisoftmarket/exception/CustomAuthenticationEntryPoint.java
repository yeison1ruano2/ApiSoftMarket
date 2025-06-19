package com.softmarket.apisoftmarket.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softmarket.apisoftmarket.dto.GenericResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.setContentType("application/json");
    GenericResponse respuesta = new GenericResponse(
            HttpStatus.UNAUTHORIZED.value(),
            "No estas autenticado. Por favor inicia sesión para acceder a este recurso"
    );
    ObjectMapper mapper = new ObjectMapper();
    String jsonResponse = mapper.writeValueAsString(respuesta);
    response.getWriter().write(jsonResponse);
  }
}
