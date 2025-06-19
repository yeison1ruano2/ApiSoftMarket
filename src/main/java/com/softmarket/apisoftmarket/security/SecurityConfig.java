package com.softmarket.apisoftmarket.security;

import com.softmarket.apisoftmarket.exception.CustomAccessDeniedHandler;
import com.softmarket.apisoftmarket.exception.CustomAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
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
  private final CustomAccessDeniedHandler customAccessDeniedHandler;
  private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

  public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, CustomAccessDeniedHandler customAccessDeniedHandler, CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
    this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    this.customAccessDeniedHandler = customAccessDeniedHandler;
    this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
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
                .requestMatchers("/api/generic/**").permitAll()
                .requestMatchers("/api/productos/**").hasAnyRole(ADMIN)
                .requestMatchers("/api/usuarios/**").hasRole(ADMIN)
                .requestMatchers("/api/inventario/entrada/**").hasRole(ADMIN)//hasAnyRole(ADMIN,CAJERO)
                .requestMatchers("/api/inventario/salida/**").hasRole(ADMIN)
                .requestMatchers("/api/inventario/**").hasRole(ADMIN)//hasAnyRole(ADMIN, CAJERO)
                .requestMatchers("/api/categoria/**").hasRole(ADMIN)
                .requestMatchers("/api/marca/**").hasRole(ADMIN)
                .requestMatchers("/api/clientes/**").hasRole(ADMIN)
                .requestMatchers("/api/ventas/**").hasRole(ADMIN)
                .requestMatchers("/api/metodoPago/**").hasRole(ADMIN)
            .anyRequest().permitAll());
            /*.exceptionHandling(exception -> exception
                    .accessDeniedHandler(customAccessDeniedHandler)
                    .authenticationEntryPoint(customAuthenticationEntryPoint))*/
        /*.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);*/

    return http.build();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.addAllowedOriginPattern("*"); // <-- TU FRONTEND
    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(List.of("*"));
    configuration.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }
}
