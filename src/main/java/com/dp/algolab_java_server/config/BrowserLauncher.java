package com.dp.algolab_java_server.config;

import java.net.URI;
import java.awt.Desktop;
import java.nio.file.Paths;
import java.nio.file.Files;
import lombok.RequiredArgsConstructor;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Component;
import org.springframework.context.event.EventListener;
import org.springframework.boot.context.event.ApplicationReadyEvent;

import com.dp.algolab_java_server.common.Logger;

@Component
@RequiredArgsConstructor
public class BrowserLauncher {
  private final Logger log;

  private static final String ENV_FILE = ".env";
  private static final String FRONTEND_URL_ENV_KEY = "FRONTEND_URL";
  private static final String BROWSER_LAUNCHER_EXECUTED_PROP = "app.browser-launcher.executed";

  @EventListener(ApplicationReadyEvent.class)
  public void launchBrowser() {
    if (isBrowserLauncherExecuted()) {
      return;
    }

    markBrowserLauncherAsCompleted();

    if (!Files.exists(Paths.get(ENV_FILE))) {
      return;
    }

    String url = getFrontendUrlFromEnvFile();

    if (url == null || url.isBlank()) {
      return;
    }

    log.info("Abriendo aplicación en el navegador");

    try {
      if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
        Desktop.getDesktop().browse(new URI(url));
      } else {
        openBrowserOsSpecific(url);
      }
    } catch (Exception e) {
      log.warning("Error al abrir la aplicación en el navegador: " + e.getMessage());
    }
  }

  private String getFrontendUrlFromEnvFile() {
    try {
      Dotenv dotenv = Dotenv.configure()
          .ignoreIfMalformed()
          .ignoreIfMissing()
          .load();

      return dotenv.get(FRONTEND_URL_ENV_KEY);
    } catch (Exception e) {
      log.warning("Error al leer la URL del frontend desde el archivo .env: " + e.getMessage());
      return null;
    }
  }

  private void openBrowserOsSpecific(String url) throws Exception {
    String os = System.getProperty("os.name").toLowerCase();

    if (os.contains("win")) {
      new ProcessBuilder("rundll32", "url.dll,FileProtocolHandler", url).start();
    } else if (os.contains("mac")) {
      new ProcessBuilder("open", url).start();
    } else if (os.contains("nix") || os.contains("nux")) {
      new ProcessBuilder("xdg-open", url).start();
    } else {
      throw new Exception("Unsupported operating system: " + os);
    }
  }

  private boolean isBrowserLauncherExecuted() {
    return System.getProperty(BROWSER_LAUNCHER_EXECUTED_PROP) != null;
  }

  private void markBrowserLauncherAsCompleted() {
    System.setProperty(BROWSER_LAUNCHER_EXECUTED_PROP, "true");
  }
}
