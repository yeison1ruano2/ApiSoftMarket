package com.softmarket.apisoftmarket.security;

import com.softmarket.apisoftmarket.repository.UserRepository;
import com.softmarket.apisoftmarket.services.impl.JwtService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final UserRepository userRepository;

  public JwtAuthenticationFilter(JwtService jwtService, UserRepository userRepository) {
    this.jwtService = jwtService;
    this.userRepository = userRepository;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
    final String authHeader = request.getHeader("Authorization");
    if(authHeader == null || !authHeader.startsWith("Bearer ")){
      chain.doFilter(request,response);
      return;
    }
    final String jwt = authHeader.substring(7);
    final String username = jwtService.extractUsername(jwt);

    if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
      var userOptional = userRepository.findByUsername(username);
      if(userOptional.isPresent() && jwtService.isValid(jwt,userOptional.get())){
        var user = userOptional.get();
        var authToken = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }
    chain.doFilter(request,response);
  }
}
