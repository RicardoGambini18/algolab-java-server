package com.dp.algolab_java_server.config.provisioner.tasks;

import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.RequiredArgsConstructor;
import io.github.cdimascio.dotenv.Dotenv;

import com.dp.algolab_java_server.common.Logger;
import com.dp.algolab_java_server.config.provisioner.ProvisioningTask;
import com.dp.algolab_java_server.config.provisioner.adapters.NetworkAdapter;
import com.dp.algolab_java_server.config.provisioner.adapters.FileSystemAdapter;

@RequiredArgsConstructor
public class DatabaseProvisioningTask implements ProvisioningTask {
  private final Logger log;
  private final NetworkAdapter network;
  private final FileSystemAdapter fileSystem;
  private static final String SQLITE_DB_FILENAME = "algolab.db";
  private static final String SQLITE_DB_GOOGLE_DRIVE_ID_ENV_KEY = "SQLITE_DB_GOOGLE_DRIVE_ID";

  @Override
  public boolean execute() {
    Path sqliteDbPath = Paths.get(SQLITE_DB_FILENAME);

    if (fileSystem.exists(sqliteDbPath)) {
      log.success("Base de datos SQLite encontrada");
      return true;
    }

    Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
    String SQLITE_DB_GOOGLE_DRIVE_ID = dotenv.get(SQLITE_DB_GOOGLE_DRIVE_ID_ENV_KEY);

    try {
      network.downloadFromGoogleDrive(SQLITE_DB_GOOGLE_DRIVE_ID, sqliteDbPath, SQLITE_DB_FILENAME);
      return true;
    } catch (Exception e) {
      log.error("Error al descargar base de datos SQLite: " + e.getMessage());
      return false;
    }
  }
}
