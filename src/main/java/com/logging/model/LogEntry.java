package com.logging.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogEntry {
    private String id;
    private String timestamp;
    private String topic;
    private String message;
    private String source;
    private String level;
    
    public LogEntry() {
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    
    public LogEntry(String topic, String message, String source) {
        this.id = java.util.UUID.randomUUID().toString().substring(0,8);
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.topic = topic;
        this.message = message;
        this.source = source;
        this.level = "INFO";
    }
    
    public LogEntry(String topic, String message, String source, String level) {
        this.id = java.util.UUID.randomUUID().toString().substring(0,8);
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.topic = topic;
        this.message = message;
        this.source = source;
        this.level = level;
    }
    
    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
    
    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
    
    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }
    
    @Override
    public String toString() {
        return String.format("[%s] %s - Topic: %s | Source: %s | Message: %s", 
                timestamp, level, topic, source, message);
    }
}