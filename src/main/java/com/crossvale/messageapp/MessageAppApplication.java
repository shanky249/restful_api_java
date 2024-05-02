package com.crossvale.messageapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessageAppApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageAppApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Starting the application...");
        SpringApplication.run(MessageAppApplication.class, args);
        LOGGER.info("Application started successfully.");
    }
}
