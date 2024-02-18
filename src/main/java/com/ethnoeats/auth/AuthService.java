package com.ethnoeats.auth;


public interface AuthService {
    String authenticate(String username, String password);

    String authenticateUser(String username, String password);

    String encodePassword(String newPassword);
}