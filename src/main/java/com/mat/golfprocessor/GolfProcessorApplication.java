package com.mat.golfprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class GolfProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(GolfProcessorApplication.class, args);
    }

}
