package com.crowdfunding.config;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class LoggingService {
    private static LoggingService instance;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private LoggingService() {}

    public static synchronized LoggingService getInstance() {
        if (instance == null) {
            instance = new LoggingService();
        }
        return instance;
    }

    public void log(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        System.out.println("[" + timestamp + "] LOG: " + message);
    }

    public void error(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        System.err.println("[" + timestamp + "] ERROR: " + message);
    }
}