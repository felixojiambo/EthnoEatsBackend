package com.ethnoeats.otp;

import com.ethnoeats.auth.EmailService;
import com.ethnoeats.auth.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class OTPServiceImpl implements OTPService {

    private static final int OTP_LENGTH =   6;
    private static final Random RANDOM = new Random();

    @Autowired
    private EmailService emailService;

    @Autowired
    private SMSService smsService;

    @Autowired
    private OTPRepository userOtpRepository;

    @Override
    public String generateOtp() {
        StringBuilder otp = new StringBuilder();
        for (int i =   0; i < OTP_LENGTH; i++) {
            otp.append(RANDOM.nextInt(10));
        }
        return otp.toString();
    }

    @Override
    public void sendOtp(String email, String phoneNumber, String otp) {
        // Determine if the emailOrPhoneNumber is an email or a phone number
        if (email.contains("@")) {
            // Send UserOtp via email
            emailService.sendOtpEmail(email, otp);
        } else {
            // Send UserOtp via SMS
            smsService.sendOtpSms(phoneNumber, otp);
        }

        // Store the UserOtp in the database
        UserOtp userOtp = new UserOtp();
        userOtp.setEmail(email); // Assuming UserOtp has a setEmail method
        userOtp.setPhoneNumber(phoneNumber); // Assuming UserOtp has a setPhoneNumber method
        userOtp.setOtp(otp); // Assuming UserOtp has a setOtp method
        userOtpRepository.save(userOtp);
}


    @Override
    public boolean verifyOtp(String phoneNumber,String email, String otp) {
        // Retrieve the stored UserOtp for the given email or phone number
        UserOtp userOtp = userOtpRepository.findByEmailOrPhoneNumber(email,phoneNumber);

        // Check if the UserOtp entity was found and if the OTP matches
        return userOtp != null && otp.equals(userOtp.getOtp());
    }




    private String getStoredOtp(String phoneNumber,String email) {
        // Retrieve the stored UserOtp from the database
        UserOtp userOtp = userOtpRepository.findByEmailOrPhoneNumber(phoneNumber,email);
        return userOtp != null ? userOtp.getOtp() : null;
    }

}
