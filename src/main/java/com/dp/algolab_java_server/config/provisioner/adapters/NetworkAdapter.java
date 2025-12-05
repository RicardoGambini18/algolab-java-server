package com.dp.algolab_java_server.config.provisioner.adapters;

import java.net.URI;
import java.nio.file.Path;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import java.net.http.HttpClient.Redirect;

import com.dp.algolab_java_server.common.Logger;
import com.dp.algolab_java_server.common.DesignPattern;

@RequiredArgsConstructor
@DesignPattern(name = "Adapter", solvedProblem = "Adapta la API genérica de 'HttpClient' a las necesidades específicas de descarga de archivos desde Google Drive, centralizando la configuración de redirecciones y manejo de errores HTTP para no contaminar la lógica de las tareas.")
public class NetworkAdapter {
  private final Logger log;
  private static final String GOOGLE_DRIVE_DOWNLOAD_URL = "https://docs.google.com/uc?export=download&id=";

  public void downloadFromGoogleDrive(String fileId, Path destination, String fileName) throws Exception {
    log.info("Descargando " + fileName + " desde Google Drive");

    try {
      HttpClient client = HttpClient.newBuilder()
          .followRedirects(Redirect.ALWAYS)
          .build();

      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(GOOGLE_DRIVE_DOWNLOAD_URL + fileId))
          .GET()
          .build();

      HttpResponse<Path> response = client.send(request, HttpResponse.BodyHandlers.ofFile(destination));

      if (response.statusCode() != 200) {
        throw new RuntimeException("HTTP status code " + response.statusCode());
      }

      log.success(fileName + " descargado correctamente");
    } catch (Exception e) {
      log.error("Error al descargar " + fileName + ": " + e.getMessage());
      throw e;
    }
  }
}
