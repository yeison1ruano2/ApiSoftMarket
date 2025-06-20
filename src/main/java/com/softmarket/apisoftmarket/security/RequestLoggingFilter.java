package com.softmarket.apisoftmarket.security;

import com.softmarket.apisoftmarket.controller.FacturaController;
import com.softmarket.apisoftmarket.entity.RequestLog;
import com.softmarket.apisoftmarket.services.RequestLogService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class RequestLoggingFilter implements Filter {

  private final RequestLogService requestLogService;
  private static final Logger logger = LoggerFactory.getLogger(FacturaController.class);

  public RequestLoggingFilter(RequestLogService requestLogService) {
    this.requestLogService = requestLogService;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;

    String ip = req.getRemoteAddr();
    String method = req.getMethod();
    String uri = req.getRequestURI();
    String origin = req.getHeader("Origin");
    String referer = req.getHeader("Referer");
    String urlFront = req.getRequestURL().toString();
    logger.info(req.getRequestURL().toString());

    RequestLog log = new RequestLog(
            ip,method,uri,origin,referer,urlFront, LocalDateTime.now()
    );
    requestLogService.guardarLog(log);
    chain.doFilter(request,response);
  }
}
