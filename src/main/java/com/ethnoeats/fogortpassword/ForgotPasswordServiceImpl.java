package com.ethnoeats.fogortpassword;
import com.ethnoeats.otp.OTPRepository;
import com.ethnoeats.auth.AuthService;
import com.ethnoeats.otp.OTPService;
import com.ethnoeats.otp.UserOtp;
import com.ethnoeats.user.model.User;
import com.ethnoeats.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private OTPService otpService;
    @Autowired
    private OTPRepository otpRepository;
    @Override
    public void requestResetPassword(String email, String phoneNumber) {

        User user = userService.findByEmailOrPhoneNumber(email,phoneNumber);
        if (user == null) {
            throw new IllegalArgumentException("User not found with email or phone number " + email + " " + phoneNumber);
        }

        String otp = otpService.generateOtp();


        otpService.sendOtp(email,phoneNumber, otp); // Corrected line


        UserOtp userOtp = new UserOtp();
        userOtp.setUserId(user.getId());
        userOtp.setOtp(otp);
        userOtp.setExpiryDate(calculateOtpExpiryDate());
        otpRepository.save(userOtp);
    }  private Date calculateOtpExpiryDate() {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,  5);
        return calendar.getTime();
    }

    @Override
    public void resetPassword(String email, String phoneNumber, String otp, String newPassword) {
        // Verify the UserOtp for the given email or phone number
        if (!otpService.verifyOtp(email,phoneNumber, otp)) {
            throw new IllegalArgumentException("Invalid UserOtp");
        }

        // Find the user by email or phone number
        User user = userService.findByEmailOrPhoneNumber(email,phoneNumber);
        if (user == null) {
            throw new IllegalArgumentException("User not found with email or phone number " + email + " " + phoneNumber);
        }

        // Reset the user's password
        user.setPassword(authService.encodePassword(newPassword));
        userService.saveUser(user);
    }
}
