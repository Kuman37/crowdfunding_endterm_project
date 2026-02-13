package com.crowdfunding.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    private static AppConfig instance;

    public AppConfig() {
        if (instance == null) {
            instance = this;
        }
    }

    public static AppConfig getInstance() {
        return instance;
    }
}