package com.softmarket.apisoftmarket.controller;

import com.softmarket.apisoftmarket.dto.GenericResponse;
import com.softmarket.apisoftmarket.entity.GenericTrash;
import com.softmarket.apisoftmarket.services.GenericTrashService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/generic")
public class GenericController {

  private final GenericTrashService genericTrashService;

  public GenericController(GenericTrashService genericTrashService) {
    this.genericTrashService = genericTrashService;
  }

  @GetMapping()
  public ResponseEntity<GenericResponse> endpointBasura(HttpServletRequest request){
    String ip = request.getHeader("X-Forwarded-For");
    if(ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)){
      ip = request.getRemoteAddr();
    }
    String genericTrash = genericTrashService.obtenerBasura();
    String mensaje = genericTrash + ip;
    return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse(HttpStatus.OK.value(),mensaje));
  }
}
