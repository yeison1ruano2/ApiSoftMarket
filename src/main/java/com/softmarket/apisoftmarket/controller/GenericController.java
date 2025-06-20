package com.softmarket.apisoftmarket.controller;

import com.softmarket.apisoftmarket.dto.GenericResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/generic")
public class GenericController {

  @GetMapping()
  public ResponseEntity<GenericResponse> endpointBasura(HttpServletRequest request){
    String ip = request.getHeader("X-Forwarded-For");
    if(ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)){
      ip = request.getRemoteAddr();
    }
    String mensaje = "Peticion recibida desde la IP: " + ip;
    return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse(HttpStatus.OK.value(),mensaje));
  }
}
