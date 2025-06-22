package com.softmarket.apisoftmarket.controller;

import com.softmarket.apisoftmarket.dto.FormaPagoResponse;
import com.softmarket.apisoftmarket.services.FormaPagoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/formaPago")
public class FormaPagoController {

  private final FormaPagoService formaPagoService;

  public FormaPagoController(FormaPagoService formaPagoService) {
    this.formaPagoService = formaPagoService;
  }

  @GetMapping
  public ResponseEntity<List<FormaPagoResponse>> listarTodos(){
    return formaPagoService.listarTodos();
  }

  @GetMapping("/{codigo}")
  public ResponseEntity<FormaPagoResponse> listarPorCodigo(@PathVariable("codigo") String codigo){
    return formaPagoService.listarPorCodigo(codigo);
  }
}
