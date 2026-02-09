package com.crowdfunding.model;

public class User extends BaseEntity {
    private String email;
    private String role;

    public User(int id, String fullName, String email, String role) {
        super(id, fullName);
        this.email = email;
        this.role = role;
    }

    @Override
    public String getSummary() {
        return "User: " + getName() + " [" + role + "]";
    }

    public String getEmail() { return email; }
    public String getRole() { return role; }

    public void setEmail(String email) { this.email = email; }
    public void setRole(String role) { this.role = role; }
}