package com.serviceDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan
@ComponentScan
public class ServicesDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicesDemoApplication.class, args);
	}

}
