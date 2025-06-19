package com.softmarket.apisoftmarket.exception;

import com.softmarket.apisoftmarket.dto.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private static final Integer BAD_REQUEST = HttpStatus.BAD_REQUEST.value();

  @ExceptionHandler(StockException.class)
  public ResponseEntity<GenericResponse> manejarStock(StockException ex){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new GenericResponse(BAD_REQUEST,ex.getMessage()));
  }

  @ExceptionHandler(InventarioException.class)
  public ResponseEntity<GenericResponse> manejarInventario(InventarioException ex){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new GenericResponse(BAD_REQUEST,ex.getMessage()));
  }

  @ExceptionHandler(ClienteException.class)
  public ResponseEntity<GenericResponse> manejarCliente(ClienteException ex){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new GenericResponse(BAD_REQUEST,ex.getMessage()));
  }

  @ExceptionHandler(ProductoException.class)
  public ResponseEntity<GenericResponse> manejarProducto(ProductoException ex){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new GenericResponse(BAD_REQUEST,ex.getMessage()));
  }

  @ExceptionHandler(UsuarioException.class)
  public ResponseEntity<GenericResponse> manejarUsuario(UsuarioException ex){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new GenericResponse(BAD_REQUEST,ex.getMessage()));
  }

  @ExceptionHandler(MarcaException.class)
  public ResponseEntity<GenericResponse> manejarMarca(MarcaException ex){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new GenericResponse(BAD_REQUEST,ex.getMessage()));
  }

  @ExceptionHandler(CategoriaException.class)
  public ResponseEntity<GenericResponse> manejarCategoria(CategoriaException ex){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new GenericResponse(BAD_REQUEST,ex.getMessage()));
  }

  @ExceptionHandler(MetodoPagoException.class)
  public ResponseEntity<GenericResponse> manejarMetodo(MetodoPagoException ex){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new GenericResponse(BAD_REQUEST,ex.getMessage()));
  }

  @ExceptionHandler(TokenException.class)
  public ResponseEntity<GenericResponse> manejarToken(TokenException ex){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new GenericResponse(BAD_REQUEST,ex.getMessage()));
  }
}
