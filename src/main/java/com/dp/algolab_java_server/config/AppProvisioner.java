package com.dp.algolab_java_server.config;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.StandardCopyOption;
import io.github.cdimascio.dotenv.Dotenv;
import java.net.http.HttpClient.Redirect;

import com.dp.algolab_java_server.common.Logger;
import com.dp.algolab_java_server.common.DesignPattern;

@DesignPattern(type = "Facade", justification = "Provee una interfaz unificada para ocultar la complejidad de la inicialización del entorno (archivos, variables, descargas), desacoplando al cliente (Main) de estos subsistemas.")
public class AppProvisioner {
  private final Logger log;
  private static final String ENV_FILENAME = ".env";
  private static final String SQLITE_DB_FILENAME = "algolab.db";
  private static final String ENV_EXAMPLE_FILENAME = ".env.example";
  private static final String DATABASE_URL_ENV_KEY = "DATABASE_URL";
  private static final String PROVISIONER_EXECUTED_PROP = "app.provisioner.executed";
  private static final String SQLITE_DB_GOOGLE_DRIVE_ID_ENV_KEY = "SQLITE_DB_GOOGLE_DRIVE_ID";
  private static final String GOOGLE_DRIVE_DOWNLOAD_URL = "https://docs.google.com/uc?export=download&id=";

  public AppProvisioner() {
    this.log = Logger.getInstance();
  }

  public void initialize() {
    if (isProvisioningCompleted()) {
      return;
    }

    System.setProperty(PROVISIONER_EXECUTED_PROP, "true");

    if (isEnvironmentConfigured()) {
      log.info("Configuración detectada. Omitiendo aprovisionamiento.");
      return;
    }

    runProvisioning();
  }

  private static boolean isProvisioningCompleted() {
    return System.getProperty(PROVISIONER_EXECUTED_PROP) != null;
  }

  private static boolean isEnvironmentConfigured() {
    String dbUrl = System.getenv(DATABASE_URL_ENV_KEY);

    if (dbUrl != null && !dbUrl.isBlank()) {
      return true;
    }

    if (Files.exists(Paths.get(ENV_FILENAME))) {
      return true;
    }

    return false;
  }

  private void runProvisioning() {
    log.info("Iniciando aprovisionamiento");

    if (!createEnvFile()) {
      System.exit(1);
    }

    if (!ensureSQLiteDatabase()) {
      System.exit(1);
    }

    log.success("Aprovisionamiento finalizado correctamente");
  }

  private boolean createEnvFile() {
    log.info("Generando archivo .env local");

    Path envPath = Paths.get(ENV_FILENAME);
    Path envExamplePath = Paths.get(ENV_EXAMPLE_FILENAME);

    try {
      Files.copy(envExamplePath, envPath, StandardCopyOption.REPLACE_EXISTING);
      log.success("Archivo .env creado correctamente");
      return true;
    } catch (Exception e) {
      log.error("Error al crear archivo .env: " + e.getMessage());
      return false;
    }
  }

  private boolean downloadFromGoogleDrive(String fileId, Path destination, String fileName) {
    log.info("Descargando " + fileName + " desde Google Drive");

    String downloadUrl = GOOGLE_DRIVE_DOWNLOAD_URL + fileId;

    try {
      HttpClient client = HttpClient.newBuilder()
          .followRedirects(Redirect.ALWAYS)
          .build();

      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(downloadUrl))
          .GET()
          .build();

      HttpResponse<Path> response = client.send(request, HttpResponse.BodyHandlers.ofFile(destination));

      if (response.statusCode() == 200) {
        log.success(fileName + " descargado correctamente");
        return true;
      } else {
        log.error("Error al descargar " + fileName + ": HTTP status code " + response.statusCode());
        Files.deleteIfExists(destination);
        return false;
      }

    } catch (Exception e) {
      log.error("Error al descargar " + fileName + ": " + e.getMessage());
      return false;
    }
  }

  private boolean ensureSQLiteDatabase() {
    Path dbPath = Paths.get(SQLITE_DB_FILENAME);

    if (Files.exists(dbPath)) {
      log.success("Base de datos SQLite encontrada");
      return true;
    }

    Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
    String SQLITE_DB_GOOGLE_DRIVE_ID = dotenv.get(SQLITE_DB_GOOGLE_DRIVE_ID_ENV_KEY);

    return downloadFromGoogleDrive(SQLITE_DB_GOOGLE_DRIVE_ID, dbPath, SQLITE_DB_FILENAME);
  }
}
