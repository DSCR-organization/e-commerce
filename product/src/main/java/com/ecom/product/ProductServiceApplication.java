package com.ecom.product;

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
                title = "Product Service REST API",
                description = "E-commerce microservice - Product service REST API documentation",
                version = "v1",
                contact = @Contact(
                        name = "Rushain Sovis aka cisco",
                        email = "sovisrushain@gmail.com",
                        url = "https://sovisrushain.github.io/portfolio/"
                ),
                license = @License(
                        name = "MIT"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "E-commerce microservice - Product service REST API documentation",
                url = "http://localhost:8082/swagger-ui/index.html"
        )
)
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

}
