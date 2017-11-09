package com.mimacom.io.sipgate.callfilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class CallFilterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CallFilterApplication.class, args);
    }

}
