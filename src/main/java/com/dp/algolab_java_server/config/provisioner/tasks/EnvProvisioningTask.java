package com.dp.algolab_java_server.config.provisioner.tasks;

import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.RequiredArgsConstructor;

import com.dp.algolab_java_server.common.Logger;
import com.dp.algolab_java_server.config.provisioner.ProvisioningTask;
import com.dp.algolab_java_server.config.provisioner.adapters.FileSystemAdapter;

@RequiredArgsConstructor
public class EnvProvisioningTask implements ProvisioningTask {
  private final Logger log;
  private final FileSystemAdapter fileSystem;

  private static final String ENV_FILENAME = ".env";
  private static final String ENV_EXAMPLE_FILENAME = ".env.example";

  @Override
  public boolean execute() {
    log.info("Creando archivo .env");

    Path envPath = Paths.get(ENV_FILENAME);
    Path envExamplePath = Paths.get(ENV_EXAMPLE_FILENAME);

    try {
      fileSystem.copy(envExamplePath, envPath);
      log.success("Archivo .env creado correctamente");
      return true;
    } catch (Exception e) {
      log.error("Error al crear archivo .env: " + e.getMessage());
      return false;
    }
  }
}
