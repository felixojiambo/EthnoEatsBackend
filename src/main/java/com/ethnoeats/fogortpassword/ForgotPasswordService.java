package com.ethnoeats.fogortpassword;

public interface ForgotPasswordService {
   // void requestResetPassword(String emailOrPhoneNumber);

    void requestResetPassword(String email, String phoneNumber);

    //void resetPassword(String emailOrPhoneNumber, String otp, String newPassword);

    void resetPassword(String email, String phoneNumber, String otp, String newPassword);

    //void resetPassword(String emailOrPhoneNumber, String otp, String newPassword);
}
