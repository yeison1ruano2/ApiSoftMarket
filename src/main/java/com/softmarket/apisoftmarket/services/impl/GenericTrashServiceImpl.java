package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.entity.GenericTrash;
import com.softmarket.apisoftmarket.repository.GenericTrashRepository;
import com.softmarket.apisoftmarket.services.GenericTrashService;
import org.springframework.stereotype.Service;

@Service
public class GenericTrashServiceImpl implements GenericTrashService {

  private final GenericTrashRepository genericTrashRepository;

  public GenericTrashServiceImpl(GenericTrashRepository genericTrashRepository) {
    this.genericTrashRepository = genericTrashRepository;
  }

  @Override
  public String obtenerBasura() {
    return genericTrashRepository.findFirstGenericTrash();
  }
}
