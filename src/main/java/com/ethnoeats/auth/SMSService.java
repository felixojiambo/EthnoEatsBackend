package com.ethnoeats.auth;


public interface SMSService {
    void sendOtpSms(String to, String otp);
}
