package com.crowdfunding.controller;

import com.crowdfunding.model.User;
import com.crowdfunding.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;
    public UserController(UserService service) { this.service = service; }

    @PostMapping
    public User create(@RequestBody User u) { return service.createUser(u); }

    @GetMapping
    public List<User> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public User get(@PathVariable Integer id) { return service.getUser(id); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) { service.deleteUser(id); }
}