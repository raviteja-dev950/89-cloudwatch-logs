package com.raviteja.cloudwatch.repository;

import com.raviteja.cloudwatch.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}