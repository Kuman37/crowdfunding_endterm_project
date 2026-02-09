package com.crowdfunding.config;

import org.springframework.stereotype.Component;

@Component
public class DatabaseConfig {
    private static DatabaseConfig instance;
    private String url;
    private String username;

    private DatabaseConfig() {
        this.url = "jdbc:postgresql://localhost:5432/crowdfunding";
        this.username = "postgres";
    }

    public static synchronized DatabaseConfig getInstance() {
        if (instance == null) {
            instance = new DatabaseConfig();
        }
        return instance;
    }

    public String getUrl() { return url; }
    public String getUsername() { return username; }
}