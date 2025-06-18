package com.softmarket.apisoftmarket.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

  private static final String ADMIN = "ADMIN";
  private static final String CAJERO = "CAJERO";

  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
    this.jwtAuthenticationFilter = jwtAuthenticationFilter;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)
        .cors(Customizer.withDefaults())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/null/**").permitAll()
            .requestMatchers("/api/auth/**").permitAll()

            .requestMatchers("/api/factura/**").permitAll()
            .requestMatchers("/api/productos/**").permitAll()
            .requestMatchers("/api/usuarios/**").hasRole(ADMIN)
            .requestMatchers("/api/inventario/entrada/**").permitAll()// hasAnyRole(ADMIN,CAJERO)
            .requestMatchers("/api/inventario/salida/**").hasAnyRole(ADMIN, CAJERO)
            .requestMatchers("/api/inventario/**").permitAll()// hasAnyRole(ADMIN, CAJERO)
            .requestMatchers("/api/categoria/**").permitAll()
            .requestMatchers("/api/marca/**").permitAll()
            .requestMatchers("/api/clientes/**").permitAll()
            .requestMatchers("/api/ventas/**").permitAll()
            .requestMatchers("/api/metodoPago/**").permitAll()
            .anyRequest().permitAll())
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOrigins(List.of("*")); // Puedes especificar dominios si quieres restringir
    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    config.setAllowedHeaders(List.of("*"));
    config.setAllowCredentials(false); // true solo si manejas cookies o autenticación basada en sesión

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);
    return source;
  }
}
