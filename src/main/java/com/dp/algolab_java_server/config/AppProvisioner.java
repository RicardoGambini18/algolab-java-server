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
    if (System.getProperty(PROVISIONER_EXECUTED_PROP) != null) {
      return;
    }

    System.setProperty(PROVISIONER_EXECUTED_PROP, "true");

    log.info("Iniciando Algolab");
    log.info("Validando configuración");

    if (!ensureEnvFile()) {
      System.exit(1);
    }

    log.success("Configuración validada correctamente");
  }

  private boolean ensureEnvFile() {
    log.info("Verificando archivo .env");

    Path envPath = Paths.get(".env");
    Path examplePath = Paths.get(".env.example");

    if (Files.exists(envPath)) {
      log.success("Archivo .env encontrado");
      return true;
    }

    try {
      Files.copy(examplePath, envPath, StandardCopyOption.REPLACE_EXISTING);
      log.success("Archivo .env creado desde .env.example correctamente");
      return true;
    } catch (Exception e) {
      log.error("No se pudo crear archivo .env: " + e.getMessage());
      return false;
    }
  }
}
