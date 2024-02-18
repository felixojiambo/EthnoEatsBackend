package com.ethnoeats.login;
import com.ethnoeats.auth.AuthResponse;
import com.ethnoeats.auth.AuthService;
import com.ethnoeats.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("/api/auth")
    public class LoginController {

        @Autowired
        private AuthService authService;

        @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
            String token = authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
            return ResponseEntity.ok(new AuthResponse(token));
        }
    }


