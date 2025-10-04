package com.ecoair.ecoair.repository;

import com.ecoair.ecoair.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
