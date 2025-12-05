package com.dp.algolab_java_server.config.provisioner.adapters;

import java.io.File;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipInputStream;
import lombok.RequiredArgsConstructor;

import com.dp.algolab_java_server.common.Logger;
import com.dp.algolab_java_server.common.DesignPattern;

@RequiredArgsConstructor
@DesignPattern(name = "Adapter", solvedProblem = "Adapta la interfaz compleja y de bajo nivel de 'java.util.zip' a un método simple y seguro requerido por el dominio, encapsulando la gestión de Streams y protegiendo la aplicación de vulnerabilidades específicas como Zip Slip.")
public class ZipAdapter {
  private final Logger log;

  public void unzipFile(Path zipFilePath, Path destDir) throws Exception {
    log.info("Descomprimiendo " + zipFilePath.getFileName());

    File dir = destDir.toFile();

    if (!dir.exists()) {
      dir.mkdirs();
    }

    try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath.toFile()))) {
      ZipEntry zipEntry = zis.getNextEntry();

      while (zipEntry != null) {
        File newFile = resolveSafeFile(dir, zipEntry);

        if (zipEntry.isDirectory()) {
          if (!newFile.isDirectory() && !newFile.mkdirs()) {
            throw new Exception("Unable to create directory " + newFile);
          }
        } else {
          File parent = newFile.getParentFile();

          if (!parent.isDirectory() && !parent.mkdirs()) {
            throw new Exception("Unable to create parent directory " + parent);
          }

          try (FileOutputStream fos = new FileOutputStream(newFile)) {
            int len;
            byte[] buffer = new byte[1024];

            while ((len = zis.read(buffer)) > 0) {
              fos.write(buffer, 0, len);
            }
          }
        }

        zipEntry = zis.getNextEntry();
      }

      log.success("Archivo " + zipFilePath.getFileName() + " descomprimido correctamente");
    } catch (Exception e) {
      log.error("Error al descomprimir archivo " + zipFilePath.getFileName() + ": " + e.getMessage());
      throw e;
    }
  }

  private File resolveSafeFile(File destinationDir, ZipEntry zipEntry) throws Exception {
    File destFile = new File(destinationDir, zipEntry.getName());

    String destDirPath = destinationDir.getCanonicalPath();
    String destFilePath = destFile.getCanonicalPath();

    if (!destFilePath.startsWith(destDirPath + File.separator)) {
      throw new Exception("Zip Slip detected on file " + zipEntry.getName());
    }

    return destFile;
  }
}
