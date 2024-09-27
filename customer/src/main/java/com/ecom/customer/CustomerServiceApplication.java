package com.ecom.customer;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Customer Service REST API",
                description = "E-commerce microservice  Customer service REST API documentation",
				version = "v1",
				contact = @Contact(
						name = "Chamila Munasinghe",
						email = "ferachamila52@gmail.com",
						url = "https://github.com/chamilab"
				),
				license = @License(
						name = "MIT"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "E-commerce microservice - Customer service REST API documentation",
				url = "http://localhost:8083/swagger-ui/index.html"
		)
)

public class CustomerServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

}
