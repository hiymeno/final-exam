CREATE TABLE IF NOT EXISTS health_logs (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    log_date TEXT NOT NULL,
    sleep_hours REAL NOT NULL,
    steps INTEGER NOT NULL,
    mood_score INTEGER NOT NULL,
    risk_level TEXT
);