package com.softmarket.apisoftmarket.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softmarket.apisoftmarket.dto.GenericResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
    response.setStatus(HttpStatus.FORBIDDEN.value());
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    GenericResponse respuesta = new GenericResponse(
            HttpStatus.FORBIDDEN.value(),
            "No tienes permisos para acceder a este recurso."
    );
    ObjectMapper mapper = new ObjectMapper();
    String jsonResponse = mapper.writeValueAsString(respuesta);
    response.getWriter().write(jsonResponse);
  }
}
