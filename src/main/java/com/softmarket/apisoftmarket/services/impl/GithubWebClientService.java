package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.dto.GitHubFileResponse;
import com.softmarket.apisoftmarket.entity.ExternalApiProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

@Service
public class GithubWebClientService {

  private final WebClient webClient;
  private static final Logger logger = LoggerFactory.getLogger(GithubWebClientService.class);
  private final ExternalApiProperties externalApiProperties;
  public GithubWebClientService(ExternalApiProperties externalApiProperties) {
    this.externalApiProperties = externalApiProperties;
    this.webClient = WebClient.builder().baseUrl(externalApiProperties.getUrlGithub()).build();
  }

  public Optional<String> obtenerArchivoauthorizacionGoogle(){
    try{
      String contenido = obtenerArchivoReactivo(externalApiProperties.getGithubOwner(), externalApiProperties.getGithubRepo(), externalApiProperties.getNameAuthorizationGoogle(), externalApiProperties.getGithubToken())
              .block();
      return Optional.ofNullable(contenido);
    }catch(Exception e ){
      logger.error("Error al obtener archivo: {}", e.getMessage());
      return Optional.empty();
    }
  }

  public Mono<String> obtenerArchivoReactivo(String owner, String repo, String filePath, String token) {
    return webClient.get()
            .uri("/repos/{owner}/{repo}/contents/{filePath}", owner, repo, filePath)
            .header("Authorization", "token " + token)
            .header("Accept", "application/vnd.github.v3+json")
            .header("User-Agent", "Java-GitHub-Client")
            .retrieve()
            .bodyToMono(GitHubFileResponse.class)
            .map(this::decodificarContenido)
            .doOnError(error -> logger.error("Error obteniendo archivo {}: {}", filePath, error.getMessage()));
  }

  private String decodificarContenido(GitHubFileResponse response){
    if (response == null || response.getContent() == null) {
      return "";
    }
    if("base64".equals(response.getEncoding())){
      byte[] decodedBytes = Base64.getDecoder().decode(
              response.getContent().replaceAll("\\s", "")
      );
      return new String(decodedBytes, StandardCharsets.UTF_8);
    }
    return response.getContent();
  }
}
