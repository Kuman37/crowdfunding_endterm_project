package com.crowdfunding.repository;

import com.crowdfunding.model.User;
import com.crowdfunding.config.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final LoggingService logger = LoggingService.getInstance();

    private final RowMapper<User> userRowMapper = (rs, rowNum) ->
            new User(
                    rs.getInt("user_id"),
                    rs.getString("full_name"),
                    rs.getString("email"),
                    rs.getString("role")
            );

    public User save(User user) {
        String sql = "INSERT INTO users (full_name, email, password_hash, role) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getName());
                ps.setString(2, user.getEmail());
                ps.setString(3, "hashed_password");
                ps.setString(4, user.getRole());
                return ps;
            }, keyHolder);

            if (keyHolder.getKey() != null) {
                user.setId(keyHolder.getKey().intValue());
            }
            logger.log("User saved with ID: " + user.getId());
            return user;

        } catch (DataAccessException e) {
            logger.error("Failed to save user: " + e.getMessage());
            throw new RuntimeException("Database error", e);
        }
    }

    public Optional<User> findById(int id) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, userRowMapper, id);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM users ORDER BY user_id";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, userRowMapper, email);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void update(User user) {
        String sql = "UPDATE users SET full_name = ?, email = ?, role = ? WHERE user_id = ?";
        jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getRole(), user.getId());
        logger.log("User updated with ID: " + user.getId());
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM users WHERE user_id = ?";
        int rows = jdbcTemplate.update(sql, id);
        logger.log("User deleted with ID: " + id);
        return rows > 0;
    }
}