package com.crowdfunding.repository;

import com.crowdfunding.model.Project;
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
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class ProjectRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final LoggingService logger = LoggingService.getInstance();

    private final RowMapper<Project> projectRowMapper = (rs, rowNum) ->
            new Project(
                    rs.getInt("project_id"),
                    rs.getString("title"),
                    rs.getInt("creator_id"),
                    rs.getInt("category_id"),
                    rs.getBigDecimal("goal_amount"),
                    rs.getString("status")
            );

    public Project save(Project project) {
        String sql = "INSERT INTO project (creator_id, category_id, title, goal_amount, start_date, end_date, status) " +
                "VALUES (?, ?, ?, ?, CURRENT_DATE, CURRENT_DATE + 30, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, project.getCreatorId());
                ps.setInt(2, project.getCategoryId());
                ps.setString(3, project.getName());
                ps.setBigDecimal(4, project.getGoalAmount());
                ps.setString(5, project.getStatus());
                return ps;
            }, keyHolder);

            if (keyHolder.getKey() != null) {
                project.setId(keyHolder.getKey().intValue());
            }
            logger.log("Project saved with ID: " + project.getId());
            return project;

        } catch (DataAccessException e) {
            logger.error("Failed to save project: " + e.getMessage());
            throw new RuntimeException("Database error", e);
        }
    }

    public Optional<Project> findById(int id) {
        String sql = "SELECT * FROM project WHERE project_id = ?";
        try {
            Project project = jdbcTemplate.queryForObject(sql, projectRowMapper, id);
            return Optional.ofNullable(project);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<Project> findAll() {
        String sql = "SELECT * FROM project ORDER BY project_id";
        return jdbcTemplate.query(sql, projectRowMapper);
    }

    public List<Project> findByCreatorId(int creatorId) {
        String sql = "SELECT * FROM project WHERE creator_id = ? ORDER BY project_id";
        return jdbcTemplate.query(sql, projectRowMapper, creatorId);
    }

    public List<Project> findByStatus(String status) {
        String sql = "SELECT * FROM project WHERE status = ? ORDER BY project_id";
        return jdbcTemplate.query(sql, projectRowMapper, status);
    }

    public void update(Project project) {
        String sql = "UPDATE project SET title = ?, status = ? WHERE project_id = ?";
        jdbcTemplate.update(sql, project.getName(), project.getStatus(), project.getId());
        logger.log("Project updated with ID: " + project.getId());
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM project WHERE project_id = ?";
        int rows = jdbcTemplate.update(sql, id);
        logger.log("Project deleted with ID: " + id);
        return rows > 0;
    }

    public BigDecimal getTotalFunding(int projectId) {
        String sql = "SELECT COALESCE(SUM(pledge_amount), 0) FROM pledge WHERE project_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, BigDecimal.class, projectId);
        } catch (DataAccessException e) {
            return BigDecimal.ZERO;
        }
    }
}