package com.guachiproject.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ActividadSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActividadSpringApplication.class, args);
	}

}
