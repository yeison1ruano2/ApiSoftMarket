package com.softmarket.apisoftmarket.mapper;

import com.softmarket.apisoftmarket.dto.ClienteRequest;
import com.softmarket.apisoftmarket.dto.ClienteResponse;
import com.softmarket.apisoftmarket.entity.Cliente;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ClienteMapper {
  public Cliente requestToEntityCreate(ClienteRequest clienteRequest) {
    return new Cliente(
            null,
            clienteRequest.getNombre(),
            clienteRequest.getApellido(),
            clienteRequest.getIdentificacion(),
            clienteRequest.getTelefono(),
            clienteRequest.getDireccion(),
            clienteRequest.getCreditoMaximo(),
            clienteRequest.getCupoTotal()
    );
  }

  public ClienteResponse entityToResponse(Cliente cliente) {
    return new ClienteResponse(
            Long.toString(cliente.getId()),
            cliente.getNombre(),
            cliente.getApellido(),
            cliente.getIdentificacion(),
            cliente.getTelefono(),
            cliente.getDireccion(),
            cliente.getCreditoMaximo(),
            cliente.getCupoTotal(),
            HttpStatus.OK.getReasonPhrase(),
            "Cliente creado con éxito"
    );
  }

  public Cliente mapCliente(Cliente cliente, ClienteRequest clienteRequest) {
    cliente.setNombre(clienteRequest.getNombre());
    cliente.setApellido(clienteRequest.getApellido());
    cliente.setIdentificacion(clienteRequest.getIdentificacion());
    cliente.setTelefono(clienteRequest.getTelefono());
    cliente.setDireccion(clienteRequest.getDireccion());
    cliente.setCreditoMaximo(clienteRequest.getCreditoMaximo());
    cliente.setCupoTotal(clienteRequest.getCupoTotal());
    return cliente;
  }
}
