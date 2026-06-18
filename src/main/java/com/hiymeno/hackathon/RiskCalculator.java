package com.hiymeno.hackathon;

public class RiskCalculator {
    public static String calculateRisk(double sleepHours, int steps, int moodScore) {
        if (sleepHours < 6) {
            if (steps < 4000) {
                return "High";
            } else {
                return moodScore <= 4 ? "High" : "Medium";
            }
        } else {
            if (steps < 4000) {
                return "Medium";
            } else {
                return moodScore <= 4 ? "Medium" : "Low";
            }
        }
    }
}