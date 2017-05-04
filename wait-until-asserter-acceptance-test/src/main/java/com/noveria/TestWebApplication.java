package com.noveria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.noveria","com.noveria.cukes"})
public class TestWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestWebApplication.class, args);
    }
}
