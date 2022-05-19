package com.krmapsserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class KrMapsServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KrMapsServerApplication.class, args);
    }

}
