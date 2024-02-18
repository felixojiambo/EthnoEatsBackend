package com.ethnoeats.signup;

import com.ethnoeats.auth.AuthService;
import com.ethnoeats.dto.SignUpRequest;
import com.ethnoeats.user.model.User;
import com.ethnoeats.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class SignUpController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest) {
        // Create a new user using the UserService
        User user = userService.createUser(signUpRequest);

        // Return a response indicating success
        return ResponseEntity.ok("User created successfully");
    }
}
