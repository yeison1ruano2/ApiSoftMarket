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

  // Configuración de tiempos
  private static final long TOKEN_DURATION_MS = 3600000; // 1 hora (60 minutos)
  private static final long SAFETY_MARGIN_MS = 300000;   // 5 minutos de seguridad
  private static final long REFRESH_DELAY_MS = 300000;  // 5 minutos

  // Configuración de retry
  private static final int MAX_RETRIES = 3;
  private static final long RETRY_BASE_DELAY_MS = 2000; // 2 segundos base

  public StartupAuthenticator(AuthenticationService authenticationService, TaskScheduler taskScheduler) {
    this.authenticationService = authenticationService;
    this.taskScheduler = taskScheduler;
  }

  @EventListener(ApplicationReadyEvent.class)
  public void inicializarToken() {
    try {
      logger.info("[INIT] 🚀 Iniciando autenticación con Factus...");

      // Usar retry también en la inicialización
      executeWithRetry(() -> {
        authenticationService.refreshTokenFactus();
        return null;
      }, "autenticación inicial");

      logger.info("[INIT] ✅ Autenticación inicial con Factus completada exitosamente");
      programarRefreshToken();

    } catch (Exception e) {
      logger.error("[INIT] 💥 ERROR CRÍTICO: Falló autenticación inicial con Factus después de {} intentos: {}",
              MAX_RETRIES, e.getMessage());
      // La aplicación puede continuar, pero sin token válido
    }
  }

  public void programarRefreshToken() {
    // Cancelar tarea anterior si existe
    if (scheduledTask != null && !scheduledTask.isCancelled()) {
      scheduledTask.cancel(false);
      logger.debug("🔄 Cancelando scheduler anterior");
    }

    Instant proximaEjecucion = Instant.now().plusMillis(REFRESH_DELAY_MS);

    scheduledTask = taskScheduler.scheduleAtFixedRate(() -> {
      logger.info("[SCHEDULED] 🔐 Refrescando token de Factus... (5 min antes de expirar)");

      try {
        // Usar retry para el refresh programado
        executeWithRetry(() -> {
          authenticationService.refreshTokenFactus();
          return null;
        }, "refresh programado");

        logger.info("[SCHEDULED] ✅ Token de Factus refrescado exitosamente");

      } catch (Exception e) {
        logger.error("[SCHEDULED] 💥 ERROR: Falló refresh de token después de {} intentos: {}",
                MAX_RETRIES, e.getMessage());
        handleTokenRefreshFailure(e);
      }

    }, proximaEjecucion, Duration.ofMillis(REFRESH_DELAY_MS));

    logSchedulerInfo();
  }

  /**
   * Ejecuta una operación con retry automático
   */
  private <T> T executeWithRetry(RetryableOperation<T> operation, String operationName) throws Exception {
    Exception lastException = null;

    for (int attempt = 1; attempt <= MAX_RETRIES; attempt++) {
      try {
        logger.debug("🔄 Intento {}/{} para {}", attempt, MAX_RETRIES, operationName);

        T result = operation.execute();

        if (attempt > 1) {
          logger.info("✅ {} exitosa en intento {}/{}", operationName, attempt, MAX_RETRIES);
        }

        return result;

      } catch (Exception e) {
        lastException = e;

        // Log específico según el tipo de error
        if (isNetworkError(e)) {
          logger.warn("🌐 Error de red en {} - intento {}/{}: {}",
                  operationName, attempt, MAX_RETRIES, e.getMessage());
        } else {
          logger.warn("⚠️ Error en {} - intento {}/{}: {}",
                  operationName, attempt, MAX_RETRIES, e.getMessage());
        }

        // Si no es el último intento, esperar antes del siguiente
        if (attempt < MAX_RETRIES) {
          long delay = RETRY_BASE_DELAY_MS * attempt; // Backoff incremental: 2s, 4s, 6s
          logger.debug("⏳ Esperando {}ms antes del siguiente intento...", delay);

          try {
            Thread.sleep(delay);
          } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Proceso interrumpido durante retry de " + operationName, ie);
          }
        }
      }
    }

    // Si llegamos aquí, todos los intentos fallaron
    throw new RuntimeException("💥 Falló " + operationName + " después de " + MAX_RETRIES + " intentos", lastException);
  }

  /**
   * Detecta si el error es de tipo red/conectividad
   */
  private boolean isNetworkError(Exception e) {
    if (e == null || e.getMessage() == null) {
      return false;
    }

    String message = e.getMessage().toLowerCase();
    return message.contains("connection reset") ||
            message.contains("connection refused") ||
            message.contains("timeout") ||
            message.contains("network") ||
            message.contains("recvaddress") ||
            message.contains("connect timed out") ||
            message.contains("read timed out");
  }

  /**
   * Maneja los fallos críticos de refresh de token
   */
  private void handleTokenRefreshFailure(Exception e) {
    logger.error("🚨 ALERTA CRÍTICA: Sistema sin token válido de Factus");
    logger.error("🚨 Todas las operaciones con Factus fallarán hasta el próximo refresh exitoso");
    logger.error("🚨 Revisar conectividad de red con api-sandbox.factus.com.co");

    // Aquí puedes agregar:
    // - Notificaciones a Slack/Teams
    // - Métricas para sistemas de monitoreo
    // - Alertas por email
    // - Health check endpoint updates
  }

  /**
   * Log información del scheduler
   */
  private void logSchedulerInfo() {
    logger.info("📅 Scheduler de token configurado:");
    logger.info("   🕐 Primera ejecución en: {} minutos ({} segundos)",
            REFRESH_DELAY_MS / 60000, REFRESH_DELAY_MS / 1000);
    logger.info("   🔄 Próximas ejecuciones cada: {} minutos", REFRESH_DELAY_MS / 60000);
    logger.info("   🛡️ Margen de seguridad: {} minutos antes de expirar", SAFETY_MARGIN_MS / 60000);
    logger.info("   🔁 Reintentos automáticos: {} intentos con backoff incremental", MAX_RETRIES);
  }

  /**
   * Interface funcional para operaciones con retry
   */
  @FunctionalInterface
  private interface RetryableOperation<T> {
    T execute() throws Exception;
  }
}