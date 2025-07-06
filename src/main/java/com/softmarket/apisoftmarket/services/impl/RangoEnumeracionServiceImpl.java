package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.repository.RangosEnumeracionRepository;
import com.softmarket.apisoftmarket.services.RangoEnumeracionService;
import org.springframework.stereotype.Service;

@Service
public class RangoEnumeracionServiceImpl implements RangoEnumeracionService {
  private final RangosEnumeracionRepository rangosEnumeracionRepository;

  public RangoEnumeracionServiceImpl(RangosEnumeracionRepository rangosEnumeracionRepository) {
    this.rangosEnumeracionRepository = rangosEnumeracionRepository;
  }

  @Override
  public Long rangoEnumeracionVenta() {
    return rangosEnumeracionRepository.findByFacturaVenta();
  }
}
