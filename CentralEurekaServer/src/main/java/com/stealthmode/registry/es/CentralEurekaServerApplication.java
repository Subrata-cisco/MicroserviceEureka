package com.stealthmode.registry.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CentralEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CentralEurekaServerApplication.class, args);
    }
}
