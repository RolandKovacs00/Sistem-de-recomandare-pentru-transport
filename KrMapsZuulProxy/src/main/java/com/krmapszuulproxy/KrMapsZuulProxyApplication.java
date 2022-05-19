package com.krmapszuulproxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class KrMapsZuulProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(KrMapsZuulProxyApplication.class, args);
    }

}
