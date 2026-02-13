package com.crowdfunding.controller;

import com.crowdfunding.model.Category;
import com.crowdfunding.service.CategoryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService service;
    public CategoryController(CategoryService service) { this.service = service; }

    @PostMapping
    public Category create(@RequestBody Category c) { return service.save(c); }

    @GetMapping
    public List<Category> getAll() { return service.getAll(); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) { service.delete(id); }
}