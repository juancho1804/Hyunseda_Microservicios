package com.api.productservice;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Product Service API")
						.version("1.0")
						.description("API hecha con Spring Boot 3.3.0, encargada de gestionar productos y categorías\n\n" +
								"Autores:\n" +
								"- Juan Manuel Cerón (juanmanuelceron@unicauca.edu.co)\n" +
								"- Juan Carlos Manquillo (jucmanquillo@unicauca.edu.co)\n" +
								"- Freddy Botia (fdbotia@unicauca.edu.co)\n" +
								"- Sharyth Velasco (sharythvelasco@unicauca.edu.co)"));

	}

}
