package com.dp.algolab_java_server.config;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.dp.algolab_java_server.common.Logger;
import com.dp.algolab_java_server.common.DesignPattern;

@DesignPattern(type = "Facade", justification = "Provee una interfaz unificada para ocultar la complejidad de la inicialización del entorno (archivos, variables, descargas), desacoplando al cliente (Main) de estos subsistemas.")
public class AppProvisioner {
  private final Logger log;
  private static final String PROVISIONER_EXECUTED_PROP = "app.provisioner.executed";

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
    String dbUrl = System.getenv("DATABASE_URL");

    if (dbUrl != null && !dbUrl.isBlank()) {
      return true;
    }

    if (Files.exists(Paths.get(".env"))) {
      return true;
    }

    return false;
  }

  private void runProvisioning() {
    log.info("Iniciando aprovisionamiento");

    if (!createEnvFile()) {
      System.exit(1);
    }

    log.success("Aprovisionamiento finalizado correctamente");
  }

  private boolean createEnvFile() {
    log.info("Generando archivo .env local");

    Path envPath = Paths.get(".env");
    Path envExamplePath = Paths.get(".env.example");

    try {
      Files.copy(envExamplePath, envPath, StandardCopyOption.REPLACE_EXISTING);
      log.success("Archivo .env creado correctamente");
      return true;
    } catch (Exception e) {
      log.error("Error al crear archivo .env: " + e.getMessage());
      return false;
    }
  }
}
