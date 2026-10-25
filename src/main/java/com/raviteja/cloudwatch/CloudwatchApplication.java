package com.raviteja.cloudwatch;

import com.raviteja.cloudwatch.repository.UserRepository;
import com.raviteja.cloudwatch.service.CloudWatchLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
public class CloudwatchApplication {

    @Autowired private UserRepository userRepository;
    @Autowired private CloudWatchLogService cloudWatchLogService;

    public static void main(String[] args) {
        SpringApplication.run(CloudwatchApplication.class, args);
    }

    @GetMapping("/")
    public Map<String, Object> home() {
        cloudWatchLogService.logInfo("Home endpoint called - totalUsers=" + userRepository.count());
        return Map.of(
            "owner", "RaviTeja - 89/100",
            "database", "H2 In-Memory (RDS-Ready)",
            "project", "89-cloudwatch-logs",
            "tier", "9 - DevOps + Cloud - CloudWatch Logs",
            "cloudWatch", cloudWatchLogService.getCloudWatchStatus(),
            "status", "Live on CloudWatch - Logs Streaming",
            "endpoints", List.of("GET /","GET /health","GET /users","POST /users","GET /users/{id}","DELETE /users/{id}","GET /logs","POST /logs/test"),
            "totalUsers", userRepository.count(),
            "dbUrl", "jdbc:h2:mem:raviteja89"
        );
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        cloudWatchLogService.logInfo("Health check - UP");
        return Map.of("status","UP","database","Connected","project","89-cloudwatch-logs","cloudWatch",cloudWatchLogService.getCloudWatchStatus());
    }
}