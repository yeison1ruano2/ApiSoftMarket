package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.controller.FacturaController;
import com.softmarket.apisoftmarket.services.AuthenticationService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class FactusTokenSchedulerService {

  private final AuthenticationService authenticationService;
  private static final Logger logger = LoggerFactory.getLogger(FactusTokenSchedulerService.class);

  public FactusTokenSchedulerService(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  @PostConstruct()
  public void inicializarToken(){
    try {
      logger.info("[INIT] Autenticando con Factus...");
      authenticationService.authenticationFactus();
    }catch(Exception e){
      logger.error("[INIT][ERROR] Falló autenticación inicial con Factus: {}", e.getMessage());
    }

  }

  @Scheduled(fixedRate = 3601000)
  public void refrescarToken(){
    System.out.println("[SCHEDULED] Refrescando token de Factus...");
    authenticationService.authenticationFactus();
  }
}
