package com.ethnoeats.auth;


public interface EmailService {
    void sendOtpEmail(String to, String otp);
}
