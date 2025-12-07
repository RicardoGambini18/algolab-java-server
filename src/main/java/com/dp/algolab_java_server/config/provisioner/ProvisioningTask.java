package com.dp.algolab_java_server.config.provisioner;

import com.dp.algolab_java_server.common.DesignPattern;
import com.dp.algolab_java_server.common.SolidPrinciple;

@DesignPattern(name = "Command", solvedProblem = "Encapsula acciones heterogéneas (descargas, copias de archivos, descompresión) bajo una interfaz común, permitiendo que el orquestador (AppProvisioner) las trate como una lista polimórfica de tareas a ejecutar secuencialmente sin conocer sus detalles internos.")
@SolidPrinciple(name = "Principio Abierto/Cerrado (OCP)", solvedProblem = "Permite agregar nuevas tareas de setup sin tocar el código del orquestador.")
@SolidPrinciple(name = "Principio de Segregación de Interfaces (ISP)", solvedProblem = "Mantiene un contrato limpio (execute) para que las tareas no implementen métodos innecesarios.")
public interface ProvisioningTask {
  boolean execute();
}
