//package com.ethnoeats.user.controller;
//
//import com.ethnoeats.user.exception.UserNotFoundException;
//import com.ethnoeats.user.model.User;
//import com.ethnoeats.user.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.data.crossstore.ChangeSetPersister;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/users")
//public class UserController {
//
//    private final UserService userService;
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping
//    public List<User> getAllUsers() {
//        return userService.getAllUsers();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getUserById(@PathVariable Long id) {
//        Optional<User> userOptional = userService.getUserById(id);
//        return userOptional.map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @PostMapping
//    public User createUser(@RequestBody User user) {
//        // Perform validation checks before saving the user
//        return userService.createUser(user);
//    }
//
//    @PutMapping("/{id}")
//    public User updateUser(@PathVariable Long id, @RequestBody User user) {
//        try {
//            return userService.updateUser(id, user);
//        } catch (EmptyResultDataAccessException e) {
//            throw new UserNotFoundException("User with id " + id + " not found", e);
//        } catch (ChangeSetPersister.NotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id);
//    }
//}
package com.ethnoeats.user.controller;

import com.ethnoeats.dto.SignUpRequest;
import com.ethnoeats.user.model.User;
import com.ethnoeats.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/signup")
    public ResponseEntity<User> createUser(@RequestBody SignUpRequest signUpRequest) {
        User user = userService.createUser(signUpRequest);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) throws ChangeSetPersister.NotFoundException {
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/search")
    public ResponseEntity<User> findByEmailOrPhoneNumber(@RequestParam String email, @RequestParam String phoneNumber) {
        User user = userService.findByEmailOrPhoneNumber(email, phoneNumber);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    // Additional endpoints for user management can be added here
}
