package com.hiymeno.hackathon;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/health-logs")
public class HealthLogController {

    private final HealthLogRepository repository;

    public HealthLogController(HealthLogRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<HealthLog> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public HealthLog create(@RequestBody HealthLog log) {
        log.setRiskLevel(RiskCalculator.calculateRisk(log.getSleepHours(), log.getSteps(), log.getMoodScore()));
        repository.insert(log);
        return log;
    }

    @PutMapping("/{id}")
    public HealthLog update(@PathVariable int id, @RequestBody HealthLog log) {
        log.setRiskLevel(RiskCalculator.calculateRisk(log.getSleepHours(), log.getSteps(), log.getMoodScore()));
        repository.update(id, log);
        log.setId(id);
        return log;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        repository.deleteById(id);
    }

    @GetMapping("/risk")
    public Map<String, String> getCurrentRisk() {
        List<HealthLog> logs = repository.findAll();
        Map<String, String> result = new HashMap<>();
        if (logs.isEmpty()) {
            result.put("riskLevel", "Unknown");
            return result;
        }
        HealthLog latest = logs.get(0);
        result.put("riskLevel", latest.getRiskLevel());
        result.put("logDate", latest.getLogDate());
        return result;
    }
}