package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.entity.DataSheetProductosIva;
import com.softmarket.apisoftmarket.repository.IvaDatSheetRepository;
import com.softmarket.apisoftmarket.services.IvaDataSheetService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.util.Locale;

@Service
public class IvaDataSheetServiceImpl implements IvaDataSheetService {

  private final IvaDatSheetRepository ivaDatSheetRepository;

  public IvaDataSheetServiceImpl(IvaDatSheetRepository ivaDatSheetRepository) {
    this.ivaDatSheetRepository = ivaDatSheetRepository;
  }

  @Override
  public BigDecimal buscarCoincidenciaCadena(String nombre) {
    try {
      String nombreSinTildeMinuscula = nombreSinTildeMinuscula(nombre);
      DataSheetProductosIva ivaProducto = ivaDatSheetRepository.buscarCoincidenciaCadena(nombreSinTildeMinuscula);
      return ivaProducto.getIva();
    }catch (NullPointerException e){
      return new BigDecimal("19.00");
    }
  }

  private String nombreSinTildeMinuscula(String nombre){
    nombre = nombre.toLowerCase();
    nombre = Normalizer.normalize(nombre,Normalizer.Form.NFD);
    nombre = nombre.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    return nombre;
  }
}
