package com.ethnoeats.otp;

import org.springframework.stereotype.Service;

@Service
public interface OTPService {

        String generateOtp();

   // void sendOtp(String emailOrPhoneNumber, String otp);

//    boolean verifyOtp(String emailOrPhoneNumber, String otp);

    void sendOtp(String email, String phoneNumber, String otp);

    boolean verifyOtp(String phoneNumber, String email, String otp);

    //void sendOtp(String , String );
}

