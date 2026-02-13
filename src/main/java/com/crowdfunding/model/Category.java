package com.crowdfunding.model;

import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class Category extends BaseEntity {

    @Column(name = "category_name", unique = true, nullable = false)
    private String categoryName;

    private String description;

    public Category() {}
    public Category(String categoryName, String description) {
        this.categoryName = categoryName;
        this.description = description;
    }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}