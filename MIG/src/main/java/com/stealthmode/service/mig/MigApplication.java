package com.stealthmode.service.mig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RefreshScope
@EnableEurekaClient
public class MigApplication {

    @Value("${hello.mig}")
    private String message;

    @RequestMapping("/msg")
    public String getMessage(){
        return this.message;
    }


    public static void main(String[] args) {
        SpringApplication.run(MigApplication.class, args);
    }
}
