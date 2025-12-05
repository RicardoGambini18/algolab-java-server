package com.dp.algolab_java_server.config.provisioner;

import java.util.List;
import java.nio.file.Paths;
import java.nio.file.Files;

import com.dp.algolab_java_server.common.Logger;
import com.dp.algolab_java_server.common.DesignPattern;
import com.dp.algolab_java_server.config.provisioner.adapters.ZipAdapter;
import com.dp.algolab_java_server.config.provisioner.adapters.NetworkAdapter;
import com.dp.algolab_java_server.config.provisioner.tasks.EnvProvisioningTask;
import com.dp.algolab_java_server.config.provisioner.adapters.FileSystemAdapter;
import com.dp.algolab_java_server.config.provisioner.tasks.DatabaseProvisioningTask;
import com.dp.algolab_java_server.config.provisioner.tasks.FrontendProvisioningTask;

@DesignPattern(name = "Facade", solvedProblem = "Oculta la complejidad y el orden de ejecución de los subsistemas de inicialización (red, sistema de archivos, descompresión), ofreciendo al método Main un único punto de entrada (initialize) para preparar todo el entorno del servidor.")
public class AppProvisioner {
  private final Logger log;
  private final List<ProvisioningTask> tasks;
  private static final String ENV_FILENAME = ".env";
  private static final String DATABASE_URL_ENV_KEY = "DATABASE_URL";
  private static final String PROVISIONER_EXECUTED_PROP = "app.provisioner.executed";

  public AppProvisioner() {
    this.log = Logger.getInstance();
    ZipAdapter zip = new ZipAdapter(log);
    NetworkAdapter network = new NetworkAdapter(log);
    FileSystemAdapter fileSystem = new FileSystemAdapter(log);

    this.tasks = List.of(
        new EnvProvisioningTask(log, fileSystem),
        new DatabaseProvisioningTask(log, network, fileSystem),
        new FrontendProvisioningTask(log, zip, network, fileSystem));
  }

  public void initialize() {
    if (isProvisioningExecuted()) {
      return;
    }

    markProvisioningAsCompleted();

    if (isEnvironmentConfigured()) {
      log.info("Configuración detectada. Omitiendo aprovisionamiento.");
      return;
    }

    runProvisioning();
  }

  private boolean isProvisioningExecuted() {
    return System.getProperty(PROVISIONER_EXECUTED_PROP) != null;
  }

  private void markProvisioningAsCompleted() {
    System.setProperty(PROVISIONER_EXECUTED_PROP, "true");
  }

  private boolean isEnvironmentConfigured() {
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

    for (ProvisioningTask task : tasks) {
      if (!task.execute()) {
        System.exit(1);
      }
    }

    log.success("Aprovisionamiento finalizado correctamente");
  }
}
