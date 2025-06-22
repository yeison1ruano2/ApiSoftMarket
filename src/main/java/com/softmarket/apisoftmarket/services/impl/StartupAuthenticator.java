package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.services.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ScheduledFuture;

@Component
public class StartupAuthenticator {

  private final AuthenticationService authenticationService;
  private static final Logger logger = LoggerFactory.getLogger(StartupAuthenticator.class);
  private final TaskScheduler taskScheduler;
  private ScheduledFuture<?> scheduledTask;
  private static final long TOKEN_DURATION_MS = 3600000; // 1 hora (60 minutos)
  private static final long SAFETY_MARGIN_MS = 300000;   // 5 minutos de seguridad
  private static final long REFRESH_DELAY_MS = 300000;//TOKEN_DURATION_MS - SAFETY_MARGIN_MS; // 55 minutos

  public StartupAuthenticator(AuthenticationService authenticationService, TaskScheduler taskScheduler) {
    this.authenticationService = authenticationService;
    this.taskScheduler = taskScheduler;
  }

  @EventListener(ApplicationReadyEvent.class)
  public void inicializarToken(){
    try {
      logger.info("[INIT] Autenticando con Factus...");
      authenticationService.refreshTokenFactus();
      logger.info("Autenticacion factus Lograda");
      programarRefreshToken();
    }catch(Exception e){
      logger.error("[INIT][ERROR] Falló autenticación inicial con Factus: {}", e.getMessage());
    }

  }

  //@Scheduled(fixedRate = 3605000, initialDelay = 3605000)
  public void programarRefreshToken(){

    if(scheduledTask != null && !scheduledTask.isCancelled()){
      scheduledTask.cancel(false);
    }

    Instant proximaEjecucion = Instant.now().plusMillis(REFRESH_DELAY_MS);

    scheduledTask = taskScheduler.scheduleAtFixedRate(() -> {
      logger.info("[SCHEDULED] Refrescando token de Factus... (5 min antes de expirar)");
      try {
        authenticationService.refreshTokenFactus();
        logger.info("Token refrescado exitosamente");
      } catch (Exception e) {
        logger.error("Error al refrescar token: {}", e.getMessage());
      }
    }, proximaEjecucion, Duration.ofMillis(REFRESH_DELAY_MS));

    logger.info("Scheduler programado:");
    logger.info("- Primera ejecución en: {} minutos ({} segundos)",
            REFRESH_DELAY_MS / 60000, REFRESH_DELAY_MS / 1000);
    logger.info("- Próximas ejecuciones cada: {} minutos", REFRESH_DELAY_MS / 60000);
    logger.info("- Margen de seguridad: {} minutos antes de expirar", SAFETY_MARGIN_MS / 60000);
  }
}
