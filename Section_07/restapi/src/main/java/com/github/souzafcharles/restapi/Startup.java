package com.github.souzafcharles.restapi;

import com.github.souzafcharles.restapi.environment.LoadEnvironment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Startup {

	public static void main(String[] args) {
		LoadEnvironment.loadEnv();
		SpringApplication.run(Startup.class, args);
	}

}