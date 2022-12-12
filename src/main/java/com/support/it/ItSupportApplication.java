package com.support.it;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info=@Info(title = "IT Support Application", version="1.0.0"))
public class ItSupportApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItSupportApplication.class, args);
	}

}
