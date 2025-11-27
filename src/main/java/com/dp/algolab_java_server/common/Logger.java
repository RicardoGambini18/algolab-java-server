package com.dp.algolab_java_server.common;

import java.time.LocalDateTime;
import org.slf4j.LoggerFactory;
import java.time.format.DateTimeFormatter;

@DesignPattern(type = "Singleton", justification = "Garantiza que exista una única instancia del Logger para centralizar la configuración de colores y tener un control ordenado de lo que se imprime en la consola.")
public class Logger {
  private static final Logger instance = new Logger();
  private final org.slf4j.Logger internalLogger;

  private static final String INFO_COLOR = "\u001B[36m";
  private static final String GRAY_COLOR = "\u001B[90m";
  private static final String RESET_COLOR = "\u001B[0m";
  private static final String ERROR_COLOR = "\u001B[31m";
  private static final String WARNING_COLOR = "\u001B[33m";
  private static final String SUCCESS_COLOR = "\u001B[32m";

  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  private Logger() {
    this.internalLogger = LoggerFactory.getLogger("Algolab");
  }

  public static Logger getInstance() {
    return instance;
  }

  private void printFormattedLog(String levelColor, String title, String message) {
    String timestamp = LocalDateTime.now().format(DATE_FORMAT);
    String coloredTimestamp = GRAY_COLOR + timestamp + RESET_COLOR;
    String coloredTitle = levelColor + title + RESET_COLOR;

    System.out.println(coloredTimestamp + " | " + coloredTitle + " | " + message);
  }

  public void info(String message) {
    if (internalLogger.isInfoEnabled()) {
      printFormattedLog(INFO_COLOR, "INFO", message);
    }
  }

  public void success(String message) {
    if (internalLogger.isInfoEnabled()) {
      printFormattedLog(SUCCESS_COLOR, "ÉXITO", message);
    }
  }

  public void warning(String message) {
    if (internalLogger.isWarnEnabled()) {
      printFormattedLog(WARNING_COLOR, "ADVERTENCIA", message);
    }
  }

  public void error(String message) {
    if (internalLogger.isErrorEnabled()) {
      printFormattedLog(ERROR_COLOR, "ERROR", message);
    }
  }
}
