package com.softmarket.apisoftmarket.services.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Collections;
import java.util.Optional;

@Service
public class GoogleDriveService {
  private final GithubWebClientService githubWebClientService;
  private static final Logger logger = LoggerFactory.getLogger(GoogleDriveService.class);

  public GoogleDriveService(GithubWebClientService githubWebClientService) {
    this.githubWebClientService = githubWebClientService;
  }

  private Drive getDriveService()throws IOException{
    Optional<String> credentialsJson = githubWebClientService.obtenerArchivoauthorizacionGoogle();
    if(credentialsJson.isEmpty()){
      throw new IOException("No se pudo obtener el archivo credentials.json desde GitHub");
    }
    InputStream credentialsStream = new ByteArrayInputStream(
            credentialsJson.get().getBytes(StandardCharsets.UTF_8)
    );
    GoogleCredential credential = GoogleCredential.fromStream(
            credentialsStream
    )
            .createScoped(Collections.singleton(DriveScopes.DRIVE));
    return new Drive.Builder(
            credential.getTransport(),
            credential.getJsonFactory(),
            credential
    ).setApplicationName("SoftMarket").build();
  }

  public void guardarImageBase64EnDrive(String base64,String nombreArchivo,String folderIdDrive)throws IOException{
    Drive driveService = getDriveService();
    File archivoDrive = new File();
    String base64ImageLimpio = base64;
    if(base64.startsWith("data:image/")){
      base64ImageLimpio = base64.substring(base64.indexOf(",") + 1);
    }
    base64ImageLimpio = base64ImageLimpio.replaceAll("\\s+", "");
    byte[] decodedBytes = Base64.getDecoder().decode(base64ImageLimpio);
    java.io.File archivoImagen = java.io.File.createTempFile(nombreArchivo,".png");
    if (folderIdDrive != null && !folderIdDrive.isEmpty()) {
      if (verificarCarpetaExiste(folderIdDrive,driveService)) {
        archivoDrive.setName(nombreArchivo + ".png");
        archivoDrive.setParents(Collections.singletonList(folderIdDrive));
      }else{
        throw new IOException("La carpeta con ID " + folderIdDrive + " no existe o no tienes permisos para acceder.");
      }
    }
    try(FileOutputStream fos = new FileOutputStream(archivoImagen)){
      fos.write(decodedBytes);
    }
    FileContent mediaContent = new FileContent("image/png",archivoImagen);
    driveService.files().create(archivoDrive,mediaContent)
            .setFields("id,name,parents,webViewLink")
            .setUploadType("multipart")
            .execute();
    try{
      Files.delete(archivoImagen.toPath());
    }catch (IOException e){
      logger.warn("No se pudo eliminar el archivo temporal: {}", archivoImagen.getName(), e);
    }
  }

  private boolean verificarCarpetaExiste(String folderId,Drive driveService) {
    try {
      File folder = driveService.files().get(folderId)
              .setFields("id,name,mimeType,capabilities")
              .execute();

      // Verificar que es una carpeta
      boolean esCarpeta = "application/vnd.google-apps.folder".equals(folder.getMimeType());

      if (esCarpeta && folder.getCapabilities() != null) {
          Boolean canAddChildren = folder.getCapabilities().getCanAddChildren();
          boolean puedeEscribir = canAddChildren != null && canAddChildren;

          if (!puedeEscribir) {
            logger.info("⚠️ ADVERTENCIA: No tienes permisos para crear archivos en esta carpeta");
          }
        }


      return esCarpeta;

    } catch (com.google.api.client.googleapis.json.GoogleJsonResponseException e) {
      switch (e.getStatusCode()) {
        case 404:
          logger.info("❌ Carpeta no encontrada o sin permisos de acceso");
          break;
        case 403:
          logger.info("❌ Permisos insuficientes para acceder a la carpeta");
          break;
        case 401:
          logger.info("❌ Credenciales inválidas o expiradas");
          break;
        default:
          logger.info("❌ Error de API: {}", e.getMessage());
      }
      return false;
    } catch (IOException e) {
      logger.info("❌ Error al verificar carpeta: {}", e.getMessage());
      return false;
    }
  }
}
