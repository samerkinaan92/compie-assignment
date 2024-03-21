package com.example.compieassignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CompieAssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompieAssignmentApplication.class, args);
    }
}
