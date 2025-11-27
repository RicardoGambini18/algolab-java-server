package com.dp.algolab_java_server.config.provisioner.adapters;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.Comparator;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import java.nio.file.StandardCopyOption;

import com.dp.algolab_java_server.common.Logger;
import com.dp.algolab_java_server.common.DesignPattern;

@RequiredArgsConstructor
@DesignPattern(name = "Adapter", justification = "Envuelve las operaciones de java.nio.Files propagando excepciones para que el cliente decida el flujo de error.")
public class FileSystemAdapter {
  private final Logger log;

  public boolean exists(Path path) {
    return Files.exists(path);
  }

  public void copy(Path source, Path target) throws Exception {
    try {
      Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
    } catch (Exception e) {
      log.error("Error al copiar archivo de " + source + " a " + target + ": " + e.getMessage());
      throw e;
    }
  }

  public void deleteIfExists(Path path) throws Exception {
    try {
      Files.deleteIfExists(path);
    } catch (Exception e) {
      log.error("Error al eliminar el archivo " + path + ": " + e.getMessage());
      throw e;
    }
  }

  public void deleteDirectoryRecursively(Path path) throws Exception {
    if (!Files.exists(path)) {
      return;
    }

    try (Stream<Path> walk = Files.walk(path)) {
      walk.sorted(Comparator.reverseOrder())
          .map(Path::toFile)
          .forEach(File::delete);
    } catch (Exception e) {
      log.error("Error al eliminar el directorio " + path + ": " + e.getMessage());
      throw e;
    }
  }
}
