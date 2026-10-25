package com.raviteja.cloudwatch.controller;

import com.raviteja.cloudwatch.service.CloudWatchLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class LogController {
    @Autowired private CloudWatchLogService cloudWatchLogService;

    @GetMapping("/logs")
    public Map<String, Object> getLogs() {
        cloudWatchLogService.logInfo("GET /logs - Fetching recent logs");
        List<String> logs = cloudWatchLogService.getRecentLogs();
        return Map.of(
            "project", "89-cloudwatch-logs",
            "cloudWatch", cloudWatchLogService.getCloudWatchStatus(),
            "totalLogs", logs.size(),
            "logs", logs,
            "note", "These logs are also pushed to AWS CloudWatch Logs if CLOUDWATCH_ENABLED=true + AWS keys set"
        );
    }

    @PostMapping("/logs/test")
    public Map<String, Object> testLogs() {
        cloudWatchLogService.logInfo("Test log - INFO - User login success");
        cloudWatchLogService.logWarn("Test log - WARN - High memory usage 80%");
        cloudWatchLogService.logError("Test log - ERROR - Simulated payment failure - For CloudWatch alarm test");
        cloudWatchLogService.logDebug("Test log - DEBUG - Fetching users count query");
        cloudWatchLogService.logInfo("Test log - INFO - All 4 levels pushed - Check /logs");
        
        return Map.of(
            "message", "5 test logs generated",
            "levels", List.of("INFO","WARN","ERROR","DEBUG","INFO"),
            "check", "GET /logs to see them",
            "cloudWatch", cloudWatchLogService.getCloudWatchStatus()
        );
    }
}