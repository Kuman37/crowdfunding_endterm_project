package com.crowdfunding.service;

import com.crowdfunding.model.User;
import com.crowdfunding.dto.UserDTO;
import com.crowdfunding.repository.UserRepository;
import com.crowdfunding.exception.*;
import com.crowdfunding.config.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final LoggingService logger = LoggingService.getInstance();

    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        logger.log("Creating new user: " + userDTO.getEmail());

        userRepository.findByEmail(userDTO.getEmail())
                .ifPresent(u -> {
                    throw new DuplicateResourceException("User with email already exists");
                });

        if (!isValidRole(userDTO.getRole())) {
            throw new InvalidInputException("Invalid role. Must be: creator, backer, or admin");
        }

        User user = new User(0, userDTO.getFullName(), userDTO.getEmail(), userDTO.getRole());
        User savedUser = userRepository.save(user);

        return convertToDTO(savedUser);
    }

    public UserDTO getUserById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

        return convertToDTO(user);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<UserDTO> getUsersByRole(String role) {
        if (!isValidRole(role)) {
            throw new InvalidInputException("Invalid role. Must be: creator, backer, or admin");
        }

        return userRepository.findAll().stream()
                .filter(user -> role.equals(user.getRole()))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserDTO updateUser(int id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

        if (!userDTO.getEmail().equals(existingUser.getEmail())) {
            userRepository.findByEmail(userDTO.getEmail())
                    .ifPresent(u -> {
                        throw new DuplicateResourceException("Email already taken");
                    });
        }

        if (!isValidRole(userDTO.getRole())) {
            throw new InvalidInputException("Invalid role. Must be: creator, backer, or admin");
        }

        existingUser.setName(userDTO.getFullName());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setRole(userDTO.getRole());

        userRepository.update(existingUser);
        logger.log("User updated with ID: " + id);

        return convertToDTO(existingUser);
    }

    @Transactional
    public void deleteUser(int id) {
        if (!userRepository.findById(id).isPresent()) {
            throw new ResourceNotFoundException("User not found with ID: " + id);
        }

        boolean deleted = userRepository.delete(id);
        if (!deleted) {
            throw new RuntimeException("Failed to delete user");
        }
        logger.log("User deleted with ID: " + id);
    }

    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setFullName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }

    private boolean isValidRole(String role) {
        return role != null && (role.equals("creator") || role.equals("backer") || role.equals("admin"));
    }
}