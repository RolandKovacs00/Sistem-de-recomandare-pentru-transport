package com.krmapsgeotools.configuration;

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
                .info(new Info().title("KRMapsApplication")
                        .description("This is the GeoTools repository microservice that doesn't use the PostgreSQL database that" +
                                "has the PostGIS extension to calculate multiple calculations that require advance formulas." +
                                "But uses the GeoTools plugin to calculate the same things as the PostGIS extension from the" +
                                " PostgreSQL.")
                        .version("v1.0.0")
                        .contact(contact)
                        .license(new License().name("OpenAPI 3 & Spring Boot").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("More about SpringDoc and OpenAPI 3")
                        .url("https://springdoc.org/"));
    }
}