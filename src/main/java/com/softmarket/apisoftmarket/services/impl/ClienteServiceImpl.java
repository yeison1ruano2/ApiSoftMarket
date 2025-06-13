package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.dto.ClienteRequest;
import com.softmarket.apisoftmarket.dto.ClienteResponse;
import com.softmarket.apisoftmarket.dto.GenericResponse;
import com.softmarket.apisoftmarket.entity.Cliente;
import com.softmarket.apisoftmarket.mapper.ClienteMapper;
import com.softmarket.apisoftmarket.repository.ClienteRepository;
import com.softmarket.apisoftmarket.services.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

  private final ClienteMapper clienteMapper;
  private final ClienteRepository clienteRepository;

  public ClienteServiceImpl(ClienteMapper clienteMapper, ClienteRepository clienteRepository) {
    this.clienteMapper = clienteMapper;
    this.clienteRepository = clienteRepository;
  }

  @Override
  public ResponseEntity<GenericResponse> crearCliente(ClienteRequest clienteRequest) {
    Cliente cliente = clienteMapper.requestToEntityCreate(clienteRequest);
    clienteRepository.save(cliente);
    return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse(HttpStatus.OK.getReasonPhrase(),"Cliente creado con éxito"));
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
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponse(HttpStatus.NOT_FOUND.getReasonPhrase(), "Cliente no encontrado"));
    }
    Cliente clienteUpdated =  clienteMapper.mapCliente(clienteOptional.get(),clienteRequest);
    clienteRepository.save(clienteUpdated);
    return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse(HttpStatus.OK.getReasonPhrase(), "Cliente actualizado con éxito"));
  }

  @Override
  public ClienteResponse obtenerClienteId(Long id) {
    Cliente cliente =  clienteRepository.findById(id).get();
    return new ClienteResponse(
            cliente.getNombre(),
            cliente.getApellido(),
            cliente.getIdentificacion(),
            cliente.getTelefono(),
            cliente.getDireccion()
    );
  }


}
