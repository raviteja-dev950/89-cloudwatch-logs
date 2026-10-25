package com.raviteja.cloudwatch.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CloudWatchLogService {
    private static final Logger log = LoggerFactory.getLogger(CloudWatchLogService.class);
    
    @Value("${aws.cloudwatch.enabled:false}")
    private boolean cloudWatchEnabled;
    
    @Value("${【entity-aws¦canonical_name=AWS】.cloudwatch.logGroup:89-cloudwatch-logs}")
    private String logGroup;
    
    @Value("${【entity-aws¦canonical_name=AWS】.cloudwatch.logStream:raviteja-89-stream}")
    private String logStream;

    private final List<String> recentLogs = Collections.synchronizedList(new ArrayList<>());

    public void logInfo(String message) {
        String fullMsg = "[INFO] " + message;
        addToMemory(fullMsg);
        log.info(message);
        if(cloudWatchEnabled) {
            // In real AWS, here we push to CloudWatch via AWS SDK
            log.info("[CloudWatch SIM] Pushing to {}/{} : {}", logGroup, logStream, message);
        }
    }
    
    public void logWarn(String message) {
        String fullMsg = "[WARN] " + message;
        addToMemory(fullMsg);
        log.warn(message);
    }
    
    public void logError(String message) {
        String fullMsg = "[ERROR] " + message;
        addToMemory(fullMsg);
        log.error(message);
    }
    
    public void logDebug(String message) {
        String fullMsg = "[DEBUG] " + message;
        addToMemory(fullMsg);
        log.debug(message);
    }

    private void addToMemory(String msg) {
        recentLogs.add(System.currentTimeMillis() + " - " + msg);
        if(recentLogs.size() > 50) recentLogs.remove(0);
    }

    public List<String> getRecentLogs() {
        List<String> copy = new ArrayList<>(recentLogs);
        Collections.reverse(copy);
        return copy;
    }

    public String getCloudWatchStatus() {
        return cloudWatchEnabled ? "Enabled - Pushing to " + logGroup + "/" + logStream : "Disabled - Local Fallback (CONSOLE + FILE) - Ready for AWS";
    }
}