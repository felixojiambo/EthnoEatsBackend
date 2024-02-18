package com.ethnoeats.otp;

import com.ethnoeats.otp.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


    @RestController
    @RequestMapping("/api/otp")
    public class OTPController {

        @Autowired
        private OTPService otpService;

        @PostMapping("/generate")
        public ResponseEntity<String> generateOtp(@RequestParam String email, @RequestParam String phoneNumber) {
            String otp = otpService.generateOtp();
            otpService.sendOtp(email, phoneNumber, otp);
            return ResponseEntity.ok(otp);
        }

        @PostMapping("/verify")
        public ResponseEntity<Boolean> verifyOtp(@RequestParam String email, @RequestParam String phoneNumber,
                                                 @RequestParam String otp) {
            boolean isValid = otpService.verifyOtp(email, phoneNumber, otp);
            return ResponseEntity.ok(isValid);
        }
    }


