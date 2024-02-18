package com.ethnoeats.user.service;

import com.ethnoeats.user.model.User;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    User saveUser(User user);

    void deleteUser(Long id);

    User getUserByUsername(String username);

    User createUser(User user);

    User updateUser(Long id, User user) throws ChangeSetPersister.NotFoundException;

   User findByEmailOrPhoneNumber(String email,String phoneNumber);
}
