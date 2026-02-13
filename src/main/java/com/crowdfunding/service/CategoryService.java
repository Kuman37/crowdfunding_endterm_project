package com.crowdfunding.service;

import com.crowdfunding.model.Category;
import com.crowdfunding.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repo;
    public CategoryService(CategoryRepository repo) { this.repo = repo; }
    public Category save(Category c) { return repo.save(c); }
    public List<Category> getAll() { return repo.findAll(); }
    public void delete(Integer id) { repo.deleteById(id); }
}