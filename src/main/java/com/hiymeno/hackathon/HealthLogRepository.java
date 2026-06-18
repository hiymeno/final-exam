package com.hiymeno.hackathon;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HealthLogRepository {

    private final JdbcTemplate jdbcTemplate;

    public HealthLogRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private HealthLog mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
        return new HealthLog(
                rs.getInt("id"),
                rs.getString("log_date"),
                rs.getDouble("sleep_hours"),
                rs.getInt("steps"),
                rs.getInt("mood_score"),
                rs.getString("risk_level")
        );
    }

    public List<HealthLog> findAll() {
        return jdbcTemplate.query("SELECT * FROM health_logs ORDER BY log_date DESC", this::mapRow);
    }

    public void insert(HealthLog log) {
        String sql = "INSERT INTO health_logs (log_date, sleep_hours, steps, mood_score, risk_level) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, log.getLogDate(), log.getSleepHours(), log.getSteps(), log.getMoodScore(), log.getRiskLevel());
    }

    public void update(int id, HealthLog log) {
        String sql = "UPDATE health_logs SET log_date = ?, sleep_hours = ?, steps = ?, mood_score = ?, risk_level = ? WHERE id = ?";
        jdbcTemplate.update(sql, log.getLogDate(), log.getSleepHours(), log.getSteps(), log.getMoodScore(), log.getRiskLevel(), id);
    }

    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM health_logs WHERE id = ?", id);
    }
}