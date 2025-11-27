package com.dp.algolab_java_server;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.dp.algolab_java_server.config.AppProperties;
import com.dp.algolab_java_server.config.AppProvisioner;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class AlgolabJavaServerApplication {
	public static void main(String[] args) {
		new AppProvisioner().initialize();
		Dotenv.configure().ignoreIfMissing().systemProperties().load();
		SpringApplication.run(AlgolabJavaServerApplication.class, args);
	}
}
