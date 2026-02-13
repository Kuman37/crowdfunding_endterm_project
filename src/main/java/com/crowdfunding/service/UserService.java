package com.crowdfunding.service;

import com.crowdfunding.model.User;
import com.crowdfunding.repository.UserRepository;
import com.crowdfunding.exception.ValidationException;
import com.crowdfunding.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) { this.repo = repo; }

    public User createUser(User u) {
        if(repo.existsByEmail(u.getEmail())) throw new ValidationException("Email exists");
        return repo.save(u);
    }
    public User getUser(Integer id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
    public List<User> getAll() { return repo.findAll(); }
    public void deleteUser(Integer id) { repo.deleteById(id); }
}