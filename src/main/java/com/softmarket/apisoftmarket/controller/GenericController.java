package com.softmarket.apisoftmarket.controller;

import com.softmarket.apisoftmarket.dto.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/generic")
public class GenericController {

  @GetMapping()
  public ResponseEntity<GenericResponse> endpointBasura(){
    return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse(HttpStatus.OK.value(),""));
  }
}
