package com.softmarket.apisoftmarket.mapper;

import com.softmarket.apisoftmarket.dto.ClienteRequest;
import com.softmarket.apisoftmarket.dto.ClienteResponse;
import com.softmarket.apisoftmarket.entity.TipoIdentificacion;
import com.softmarket.apisoftmarket.entity.Cliente;
import com.softmarket.apisoftmarket.entity.TipoCliente;
import com.softmarket.apisoftmarket.services.TipoClienteService;
import com.softmarket.apisoftmarket.services.TipoIdentificacionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ClienteMapper {

  private final TipoClienteService tipoClienteService;
  private final TipoIdentificacionService tipoIdentificacionService;

  public ClienteMapper(TipoClienteService tipoClienteService, TipoIdentificacionService tipoIdentificacionService) {
    this.tipoClienteService = tipoClienteService;
    this.tipoIdentificacionService = tipoIdentificacionService;
  }

  public Cliente requestToEntityCreate(ClienteRequest clienteRequest, TipoIdentificacion tipoIdentificacion, TipoCliente tipoCliente) {
    return new Cliente(
            clienteRequest.getNombre(),
            clienteRequest.getApellido(),
            tipoIdentificacion,
            clienteRequest.getIdentificacion(),
            clienteRequest.getTelefono(),
            clienteRequest.getDireccion(),
            clienteRequest.getCorreoElectronico(),
            clienteRequest.getCreditoMaximo(),
            clienteRequest.getCupoTotal(),
            tipoCliente
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
            ""
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
    cliente.setTipoCliente(tipoClienteService.obtenerTipoClienteNombre(clienteRequest.getTipoCliente()));
    cliente.setCorreoElectronico(cliente.getCorreoElectronico());
    cliente.setTipoIdentificacion(tipoIdentificacionService.obtenerTipoIdentificacionNombre(clienteRequest.getTipoIdentificacion()));
    return cliente;
  }
}
