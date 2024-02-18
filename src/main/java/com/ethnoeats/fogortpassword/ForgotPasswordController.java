package com.ethnoeats.fogortpassword;
import com.ethnoeats.fogortpassword.ForgotPasswordService;
import com.ethnoeats.otp.OTPService;
import com.ethnoeats.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
    @RestController
    @RequestMapping("/api/forgot-password")
    public class ForgotPasswordController {

        @Autowired
        private ForgotPasswordService forgotPasswordService;

        @Autowired
        private OTPService otpService;

        @PostMapping("/request-reset")
        public ResponseEntity<Void> requestResetPassword(@RequestParam String email, @RequestParam String phoneNumber) {
            forgotPasswordService.requestResetPassword(email, phoneNumber);
            return ResponseEntity.ok().build();
        }

        @PostMapping("/reset")
        public ResponseEntity<Void> resetPassword(@RequestParam String email, @RequestParam String phoneNumber,
                                                  @RequestParam String otp, @RequestParam String newPassword) {
            forgotPasswordService.resetPassword(email, phoneNumber, otp, newPassword);
            return ResponseEntity.ok().build();
        }
    }
