package com.logging.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/logs")
public class LoggingController {
    
    @GetMapping
    public String health() {
        return "81-logging-service RUNNING - Port 8090 - Listening to orders + payments topics, Logging all events!";
    }
    
    @GetMapping("/health")
    public String health2() {
        return "81-logging-service RUNNING - Port 8090 - Listening to orders + payments topics, Logging all events!";
    }
}