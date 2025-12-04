package com.dp.algolab_java_server.config.provisioner.tasks;

import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.RequiredArgsConstructor;
import io.github.cdimascio.dotenv.Dotenv;

import com.dp.algolab_java_server.common.Logger;
import com.dp.algolab_java_server.config.provisioner.ProvisioningTask;
import com.dp.algolab_java_server.config.provisioner.adapters.ZipAdapter;
import com.dp.algolab_java_server.config.provisioner.adapters.NetworkAdapter;
import com.dp.algolab_java_server.config.provisioner.adapters.FileSystemAdapter;

@RequiredArgsConstructor
public class FrontendProvisioningTask implements ProvisioningTask {
  private final Logger log;
  private final ZipAdapter zip;
  private final NetworkAdapter network;
  private final FileSystemAdapter fileSystem;
  private static final String FRONTEND_DIR = "frontend";
  private static final String ZIP_FILENAME = "frontend.zip";
  private static final String INDEX_HTML_FILENAME = "index.html";
  private static final String FRONTEND_ZIP_GOOGLE_DRIVE_ID_ENV_KEY = "FRONTEND_ZIP_GOOGLE_DRIVE_ID";

  @Override
  public boolean execute() {
    try {
      if (fileSystem.exists(Paths.get(FRONTEND_DIR, INDEX_HTML_FILENAME))) {
        log.success("Archivos del frontend encontrados");
        return true;
      }

      Dotenv dotenv = Dotenv.configure().ignoreIfMissing().ignoreIfMalformed().load();
      String FRONTEND_ZIP_GOOGLE_DRIVE_ID = dotenv.get(FRONTEND_ZIP_GOOGLE_DRIVE_ID_ENV_KEY);

      log.info("Eliminando directorio " + FRONTEND_DIR + " y archivo " + ZIP_FILENAME + " si existen");

      fileSystem.deleteDirectoryRecursively(Paths.get(FRONTEND_DIR));
      fileSystem.deleteIfExists(Paths.get(ZIP_FILENAME));

      Path zipFilePath = Paths.get(ZIP_FILENAME);

      network.downloadFromGoogleDrive(FRONTEND_ZIP_GOOGLE_DRIVE_ID, zipFilePath, ZIP_FILENAME);
      zip.unzipFile(zipFilePath, Paths.get("."));
      fileSystem.deleteIfExists(zipFilePath);

      log.success("Archivos del frontend descargados correctamente");

      return true;
    } catch (Exception e) {
      log.error("Error al descargar los archivos del frontend: " + e.getMessage());
      return false;
    }
  }
}
