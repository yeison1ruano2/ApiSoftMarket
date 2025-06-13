package com.softmarket.apisoftmarket.controller;

import com.softmarket.apisoftmarket.dto.ClienteRequest;
import com.softmarket.apisoftmarket.dto.ClienteResponse;
import com.softmarket.apisoftmarket.dto.GenericResponse;
import com.softmarket.apisoftmarket.services.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

  private final ClienteService clienteService;

  public ClienteController(ClienteService clienteService) {
    this.clienteService = clienteService;
  }

  @PostMapping()
  public ResponseEntity<GenericResponse> crearCliente(@RequestBody ClienteRequest clienteRequest){
    return clienteService.crearCliente(clienteRequest);
  }

  @GetMapping()
  public ResponseEntity<List<ClienteResponse>> listarClientes(){
    return clienteService.listarClientes();
  }

  @GetMapping("/{termino}")
  public ResponseEntity<List<ClienteResponse>> buscarTermino(@PathVariable String termino){
    return clienteService.buscarTermino(termino);
  }

  @PutMapping()
  public ResponseEntity<GenericResponse> actualizarCliente(@RequestParam("id") String id, @RequestBody ClienteRequest clienteRequest){
    return clienteService.actualizarCliente(id,clienteRequest);
  }
}
