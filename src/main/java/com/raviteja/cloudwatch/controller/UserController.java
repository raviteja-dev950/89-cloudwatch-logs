package com.raviteja.cloudwatch.controller;

import com.raviteja.cloudwatch.entity.User;
import com.raviteja.cloudwatch.repository.UserRepository;
import com.raviteja.cloudwatch.service.CloudWatchLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired private UserRepository userRepository;
    @Autowired private CloudWatchLogService cloudWatchLogService;

    @GetMapping
    public List<User> getAll() {
        cloudWatchLogService.logInfo("GET /users - Fetching all users");
        cloudWatchLogService.logDebug("Hibernate: select * from users");
        return userRepository.findAll();
    }

    @PostMapping
    public User create(@RequestBody User user) {
        cloudWatchLogService.logInfo("POST /users - Creating user: " + user.getName() + " email=" + user.getEmail());
        User saved = userRepository.save(user);
        cloudWatchLogService.logInfo("User saved successfully id=" + saved.getId() + " - Pushing log to CloudWatch if enabled");
        return saved;
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        cloudWatchLogService.logDebug("GET /users/" + id + " - Fetching by id");
        return userRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public Map<String,String> delete(@PathVariable Long id) {
        cloudWatchLogService.logWarn("DELETE /users/" + id + " - Deleting user");
        userRepository.deleteById(id);
        cloudWatchLogService.logInfo("User deleted id=" + id);
        return Map.of("message","Deleted id " + id);
    }
}