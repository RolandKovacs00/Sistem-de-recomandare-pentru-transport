package com.krmapsclientrepository.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI springShopOpenAPI() {

        Contact contact = new Contact();
        contact.setEmail("roland.kovacs00@e-uvt.ro");
        contact.setName("Kovacs Roland");

        return new OpenAPI()
                .info(new Info().title("WebGISApplication")
                        .description("This is the client repository microservice that uses the PostgreSQL database that " +
                                "has the PostGIS extension to calculate multiple calculations that require advance formulas.")
                        .version("v1.0.0")
                        .contact(contact)
                        .license(new License().name("OpenAPI 3 & Spring Boot").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("More about SpringDoc and OpenAPI 3")
                        .url("https://springdoc.org/"));
    }
}