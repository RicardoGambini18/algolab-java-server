package com.dp.algolab_java_server.config.provisioner;

import com.dp.algolab_java_server.common.DesignPattern;

@DesignPattern(name = "Command", justification = "Interfaz común para encapsular cada paso de aprovisionamiento como una tarea independiente.")
public interface ProvisioningTask {
  boolean execute();
}
