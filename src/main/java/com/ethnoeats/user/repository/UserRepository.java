package com.ethnoeats.user.repository;

import com.ethnoeats.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    User findByPhoneNumber(String phoneNumber);
    boolean existsByUsername(String username);
}
