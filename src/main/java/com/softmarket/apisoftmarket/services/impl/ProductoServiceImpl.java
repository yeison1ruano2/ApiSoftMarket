package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.dto.*;
import com.softmarket.apisoftmarket.entity.*;
import com.softmarket.apisoftmarket.exception.ProductoException;
import com.softmarket.apisoftmarket.exception.ProductoInfoWebException;
import com.softmarket.apisoftmarket.mapper.ProductoMapper;
import com.softmarket.apisoftmarket.repository.ProductoRepository;
import com.softmarket.apisoftmarket.services.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Objects;

@Service
public class ProductoServiceImpl implements ProductoService {

  private final ProductoRepository productoRepository;
  private final ProductoMapper productoMapper;
  private final InventarioService inventarioService;
  private final IvaDataSheetService ivaDataSheetService;
  public ProductoServiceImpl(ProductoRepository productoRepository, ProductoMapper productoMapper, InventarioService inventarioService, IvaDataSheetService ivaDataSheetService) {
    this.productoRepository = productoRepository;
    this.productoMapper = productoMapper;
    this.inventarioService = inventarioService;
    this.ivaDataSheetService = ivaDataSheetService;
  }

  @Override
  public ResponseEntity<GenericResponse> crearProducto(ProductoRequest productoRequest) {
    try {
      BigDecimal ivaProducto = ivaDataSheetService.buscarCoincidenciaCadena(productoRequest.getNombre());
      Producto producto = productoMapper.requestToEntityCreate(productoRequest,ivaProducto);
      producto  = productoRepository.save(producto);
      inventarioService.crearInventario(producto.getId(),productoRequest.getStockMinimo());
      return ResponseEntity.status(HttpStatus.CREATED).body(new GenericResponse(HttpStatus.CREATED.value(), "Producto creado con éxito"));
    } catch (DataIntegrityViolationException e) {
      if (Objects.requireNonNull(e.getRootCause()).getMessage().toLowerCase().contains("codigo_barras") && e.getRootCause() != null) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                  .body(new GenericResponse(HttpStatus.BAD_REQUEST.value(), "El código de barras ya existe"));
        }
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
              .body(new GenericResponse(HttpStatus.BAD_REQUEST.value(), "Error de integridad en la base de datos"));
    }
  }

  @Override
  public ResponseEntity<List<ProductoResponse>> listarTodos() {
    List<ProductoResponse> productosResponse = productoRepository.findAll()
              .stream()
              .map(producto -> {
                Integer stockProducto = inventarioService.obtenerStock(producto.getCodigoBarras());
                return productoMapper.entityToResponse(producto,stockProducto);
              })
              .toList();
    if(productosResponse.isEmpty()){
      throw new ProductoException("Lista de productos vacia");
    }
    return ResponseEntity.status(HttpStatus.OK).body(productosResponse);
  }

  @Override
  public ResponseEntity<List<ProductoResponse>> obtenerProductoNombre(String nombre) {
    List<ProductoResponse> productoResponses = productoRepository.findByNombre(nombre)
            .stream()
            .map(producto->{
              Integer stockProducto = inventarioService.obtenerStock(producto.getCodigoBarras());
              return productoMapper.entityToResponse(producto,stockProducto);
            })
            .toList();
    return ResponseEntity.status(HttpStatus.OK).body(productoResponses);
  }


  @Override
  public ResponseEntity<List<ProductoResponse>> obtenerProductoBarras(String codigoBarras) {
    Integer stockProducto = inventarioService.obtenerStock(codigoBarras);
    List<ProductoResponse> productResponses = productoRepository.findByCodigoBarras(codigoBarras)
            .stream()
            .map(producto -> productoMapper.entityToResponse(producto,stockProducto))
            .toList();
    return ResponseEntity.status(HttpStatus.OK).body(productResponses);
  }

  @Override
  public ResponseEntity<GenericResponse> actualizarProducto(String codigoBarras, ProductoRequest productoRequest) {
    return productoRepository.findByCodigoBarras(codigoBarras)
            .map(producto -> {
              producto = productoMapper.requestToEntityUpdate(producto,productoRequest);
              productoRepository.save(producto);
              return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse(HttpStatus.OK.value(), "Producto actualizado con éxito"));
            })
            .orElseThrow(()->new ProductoException("Producto no encontrado"));
  }

  @Override
  public ProductoInfoWebResponse obtenerInfoProductoWeb(String codigoBarras){
    try {
      String htmlContent = makeHttpRequest(codigoBarras);
      String productName = getProductName(htmlContent);
      BigDecimal ivaProducto = ivaDataSheetService.buscarCoincidenciaCadena(productName);
      return parseProductoInfo(htmlContent,codigoBarras,ivaProducto);
    } catch (IOException e) {
      throw new ProductoInfoWebException("Ocurrio un error al obtener la información del producto");
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new ProductoInfoWebException("Proceso de información de producto interrumpido");
    }
  }

  private String makeHttpRequest(String barcode) throws IOException, InterruptedException {
      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder()
              .uri(URI.create("https://go-upc.com/search?q=" + barcode))
              .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
              .GET()
              .build();
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      return response.body();
  }

  public ProductoInfoWebResponse parseProductoInfo(String htmlContent,String codigoBarras,BigDecimal ivaProducto){
    Document doc = Jsoup.parse(htmlContent);
    String productName = "";
    Element productNameElement = doc.selectFirst("h1.product-name");
    if (productNameElement != null) {
      productName = productNameElement.text().trim();
    }
    String brand = "";
    String category = "";
    Elements tableRows = doc.select("table.table-striped tr");
    for (Element row : tableRows) {
      Elements cells = row.select("td");
      if (cells.size() >= 2) {
        String label = cells.get(0).text().trim();
        String value = cells.get(1).text().trim();

        if ("Brand".equals(label)) {
          brand = value;
        } else if ("Category".equals(label)) {
          category = value;
        }
      }
    }
    return new ProductoInfoWebResponse(brand, category, productName,codigoBarras,ivaProducto);
  }

  private String getProductName (String htmlContent){
    Document doc = Jsoup.parse(htmlContent);
    String productName = "";
    Element productNameElement = doc.selectFirst("h1.product-name");
    if (productNameElement != null) {
      productName = productNameElement.text().trim();
    }
    return productName;
  }
}
