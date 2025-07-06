package com.softmarket.apisoftmarket.dto;

import java.util.List;

public class PreconfiguradoResponse {

  private List<String> marcas;
  private List<String> categorias;
  private List<ProductoResponse> productos;
  private List<String> metodosDePagos;

  public  PreconfiguradoResponse(List<String> marcas, List<String> categorias, List<ProductoResponse> productos,List<String> metodosDePagos) {
    this.marcas = marcas;
    this.categorias = categorias;
    this.productos = productos;
    this.metodosDePagos = metodosDePagos;
  }

  public PreconfiguradoResponse() {
  }

  public List<String> getMetodosDePagos() {
    return metodosDePagos;
  }

  public void setMetodosDePagos(List<String> metodosDePagos) {
    this.metodosDePagos = metodosDePagos;
  }

  public List<String> getCategorias() {
    return categorias;
  }

  public void setCategorias(List<String> categorias) {
    this.categorias = categorias;
  }

  public List<String> getMarcas() {
    return marcas;
  }

  public void setMarcas(List<String> marcas) {
    this.marcas = marcas;
  }

  public List<ProductoResponse> getProductos() {
    return productos;
  }

  public void setProductos(List<ProductoResponse> productos) {
    this.productos = productos;
  }
}
