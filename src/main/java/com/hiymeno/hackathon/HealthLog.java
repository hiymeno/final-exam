package com.hiymeno.hackathon;

public class HealthLog {
    private int id;
    private String logDate;
    private double sleepHours;
    private int steps;
    private int moodScore;
    private String riskLevel;

    public HealthLog() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getLogDate() { return logDate; }
    public void setLogDate(String logDate) { this.logDate = logDate; }
    public double getSleepHours() { return sleepHours; }
    public void setSleepHours(double sleepHours) { this.sleepHours = sleepHours; }
    public int getSteps() { return steps; }
    public void setSteps(int steps) { this.steps = steps; }
    public int getMoodScore() { return moodScore; }
    public void setMoodScore(int moodScore) { this.moodScore = moodScore; }
    public String getRiskLevel() { return riskLevel; }
    public void setRiskLevel(String riskLevel) { this.riskLevel = riskLevel; }
}