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
        HealthLog log = new HealthLog();
        log.setId(rs.getInt("id"));
        log.setLogDate(rs.getString("log_date"));
        log.setSleepHours(rs.getDouble("sleep_hours"));
        log.setSteps(rs.getInt("steps"));
        log.setMoodScore(rs.getInt("mood_score"));
        log.setRiskLevel(rs.getString("risk_level"));
        return log;
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