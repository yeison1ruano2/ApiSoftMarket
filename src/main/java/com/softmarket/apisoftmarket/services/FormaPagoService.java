package com.softmarket.apisoftmarket.services;

import com.softmarket.apisoftmarket.dto.FormaPagoResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FormaPagoService {
  ResponseEntity<List<FormaPagoResponse>> listarTodos();
  ResponseEntity<FormaPagoResponse> listarPorCodigo(String codigo);
  List<String> obtenerMetodosDePagoList();
}
