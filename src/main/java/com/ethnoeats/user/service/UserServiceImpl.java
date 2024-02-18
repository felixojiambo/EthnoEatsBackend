package com.ethnoeats.user.service;

import com.ethnoeats.dto.SignUpRequest;
import com.ethnoeats.user.model.User;
import com.ethnoeats.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        validateUser(user);
        String username = generateUniqueUsername(user.getFirstName(), user.getLastName());
        user.setUsername(username);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private void validateUser(User user) {
        if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }
        // Add more validation checks as needed
    }

    private String generateUniqueUsername(String firstName, String lastName) {
        String baseUsername = firstName.substring(0,  1).toLowerCase() + lastName.toLowerCase();
        String username = baseUsername;
        int count =  1;
        while (userRepository.existsByUsername(username)) {
            username = baseUsername + count;
            count++;
        }
        return username;
    }

    @Override
    public User createUser(User user) {
        validateUser(user);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());

        return userRepository.save(existingUser);
    }

    @Override
    public User findByEmailOrPhoneNumber(String email, String phoneNumber) {
        // First, try to find the user by email
        User userByEmail = userRepository.findByEmail(email);
        if (userByEmail != null) {
            return userByEmail;
        }

        // If not found by email, try to find by phone number
        User userByPhoneNumber = userRepository.findByPhoneNumber(phoneNumber);
        return userByPhoneNumber;
    }


    public User createUser(SignUpRequest signUpRequest) {
        // Encode the password
        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());

        // Create a new User object
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(encodedPassword);

        // Save the user to the database
        return userRepository.save(user);
    }
}
