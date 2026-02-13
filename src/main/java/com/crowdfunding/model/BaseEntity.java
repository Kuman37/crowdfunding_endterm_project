package com.crowdfunding.model;

import jakarta.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
}