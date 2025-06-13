package com.softmarket.apisoftmarket.security;

import com.softmarket.apisoftmarket.repository.UserRepository;
import com.softmarket.apisoftmarket.services.impl.JwtService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter implements Filter {

  private final JwtService jwtService;
  private final UserRepository userRepository;

  public JwtAuthenticationFilter(JwtService jwtService, UserRepository userRepository) {
    this.jwtService = jwtService;
    this.userRepository = userRepository;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    var httpReq = (HttpServletRequest) request;
    String authHeader = httpReq.getHeader("Authorization");

    if(authHeader != null && authHeader.startsWith("Bearer ")){
      String token = authHeader.substring(7);
      String username = jwtService.extractUsername(token);

      var user = userRepository.findByUsername(username).orElse(null);
      if(user != null){
        var authToken = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }
    chain.doFilter(request,response);
  }
}
