package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.dto.ClienteRequest;
import com.softmarket.apisoftmarket.dto.ClienteResponse;
import com.softmarket.apisoftmarket.dto.GenericResponse;
import com.softmarket.apisoftmarket.entity.TipoIdentificacion;
import com.softmarket.apisoftmarket.entity.Cliente;
import com.softmarket.apisoftmarket.entity.TipoCliente;
import com.softmarket.apisoftmarket.exception.ClienteException;
import com.softmarket.apisoftmarket.mapper.ClienteMapper;
import com.softmarket.apisoftmarket.repository.ClienteRepository;
import com.softmarket.apisoftmarket.services.ClienteService;
import com.softmarket.apisoftmarket.services.TipoClienteService;
import com.softmarket.apisoftmarket.services.TipoIdentificacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

  private final ClienteMapper clienteMapper;
  private final ClienteRepository clienteRepository;
  private final TipoClienteService tipoClienteService;
  private final TipoIdentificacionService tipoIdentificacionService;

  public ClienteServiceImpl(ClienteMapper clienteMapper, ClienteRepository clienteRepository, TipoClienteService tipoClienteService, TipoIdentificacionService tipoIdentificacionService) {
    this.clienteMapper = clienteMapper;
    this.clienteRepository = clienteRepository;
    this.tipoClienteService = tipoClienteService;
    this.tipoIdentificacionService = tipoIdentificacionService;
  }

  @Override
  public ResponseEntity<GenericResponse> crearCliente(ClienteRequest clienteRequest) {
    TipoIdentificacion tipoIdentificacion = tipoIdentificacionService.obtenerTipoIdentificacionNombre(clienteRequest.getTipoIdentificacion());
    TipoCliente tipoCliente =  tipoClienteService.obtenerTipoClienteNombre(clienteRequest.getTipoCliente());
    Cliente cliente = clienteMapper.requestToEntityCreate(clienteRequest,tipoIdentificacion,tipoCliente);
    clienteRepository.save(cliente);
    return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse(HttpStatus.OK.value(),"Cliente creado con éxito"));
  }

  @Override
  public ResponseEntity<List<ClienteResponse>> listarClientes() {
    List<ClienteResponse> clientesResponses =  clienteRepository.findAll()
            .stream()
            .map(clienteMapper::entityToResponse)
            .toList();
    return ResponseEntity.status(HttpStatus.OK).body(clientesResponses);
  }

  @Override
  public ResponseEntity<List<ClienteResponse>> buscarTermino(String termino) {
    List<ClienteResponse> clienteResponses = clienteRepository.buscarPorTermino(termino)
            .stream()
            .map(clienteMapper::entityToResponse)
            .toList();
    return ResponseEntity.status(HttpStatus.OK).body(clienteResponses);
  }

  @Override
  public ResponseEntity<GenericResponse> actualizarCliente(String id, ClienteRequest clienteRequest) {
    Optional<Cliente> clienteOptional =  clienteRepository.findById(Long.parseLong(id));
    if(clienteOptional.isEmpty()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponse(HttpStatus.NOT_FOUND.value(), "Cliente no encontrado"));
    }
    Cliente clienteUpdated =  clienteMapper.mapCliente(clienteOptional.get(),clienteRequest);
    clienteRepository.save(clienteUpdated);
    return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse(HttpStatus.OK.value(), "Cliente actualizado con éxito"));
  }

  @Override
  public ClienteResponse obtenerClienteId(Long id) {
    Cliente cliente =  clienteRepository.findById(id).orElseThrow(()-> new ClienteException("Cliente no encontrado"));
    return new ClienteResponse(
            cliente.getNombre(),
            cliente.getApellido(),
            cliente.getIdentificacion(),
            cliente.getTelefono(),
            cliente.getDireccion()
    );
  }


}
