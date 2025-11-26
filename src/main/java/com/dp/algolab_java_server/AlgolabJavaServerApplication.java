package com.dp.algolab_java_server;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.dp.algolab_java_server.config.AlgolabProperties;

@SpringBootApplication
@EnableConfigurationProperties(AlgolabProperties.class)
public class AlgolabJavaServerApplication {
	public static void main(String[] args) {
		Dotenv.configure().ignoreIfMissing().systemProperties().load();
		SpringApplication.run(AlgolabJavaServerApplication.class, args);
	}
}
