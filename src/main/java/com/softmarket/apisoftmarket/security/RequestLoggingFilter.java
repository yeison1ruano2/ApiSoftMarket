package com.softmarket.apisoftmarket.security;

import com.softmarket.apisoftmarket.entity.RequestLog;
import com.softmarket.apisoftmarket.services.RequestLogService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class RequestLoggingFilter implements Filter {

  private final RequestLogService requestLogService;

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

    RequestLog log = new RequestLog(
            ip,method,uri,origin,referer, LocalDateTime.now()
    );
    requestLogService.guardarLog(log);
    chain.doFilter(request,response);
  }
}
