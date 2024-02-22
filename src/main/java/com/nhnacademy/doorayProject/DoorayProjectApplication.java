package com.nhnacademy.doorayProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class DoorayProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoorayProjectApplication.class, args);
	}

}
