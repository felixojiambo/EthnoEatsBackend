package com.ethnoeats.signup;

import com.ethnoeats.auth.AuthService;
import com.ethnoeats.otp.OTPService;
import com.ethnoeats.user.model.User;
import com.ethnoeats.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private OTPService otpService; // Autowire the OTPService instance

    @Override
    public void signUp(String firstName, String lastName, String email, String phoneNumber) {
        // Create a new user object
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);

        // Save the user
        userService.saveUser(user);

        // Generate an OTP
        String otp = otpService.generateOtp();

        // Send the OTP to the user's email and phone number
        otpService.sendOtp(email, phoneNumber, otp); // Corrected line
    }


    @Override
    public boolean verifyOtp(String email,String phoneNumber, String otp) {
        // Verify the OTP for the given email or phone number
        return otpService.verifyOtp(email,phoneNumber, otp);
    }
}


