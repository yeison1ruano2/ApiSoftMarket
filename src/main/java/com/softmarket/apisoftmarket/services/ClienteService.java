package com.softmarket.apisoftmarket.services;

import com.softmarket.apisoftmarket.dto.ClienteRequest;
import com.softmarket.apisoftmarket.dto.ClienteResponse;
import com.softmarket.apisoftmarket.dto.GenericResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClienteService {
  ResponseEntity<GenericResponse> crearCliente(ClienteRequest clienteRequest);

  ResponseEntity<List<ClienteResponse>> listarClientes();

  ResponseEntity<List<ClienteResponse>> buscarTermino(String termino);

  ResponseEntity<GenericResponse> actualizarCliente(String id, ClienteRequest clienteRequest);

  ClienteResponse obtenerClienteId(Long id);
}
