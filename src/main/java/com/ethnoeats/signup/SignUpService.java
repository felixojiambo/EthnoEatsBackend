package com.ethnoeats.signup;

public interface SignUpService {

    void signUp(String firstName, String lastName, String email, String phoneNumber);
    boolean verifyOtp(String email,String phoneNumber, String otp);
}
