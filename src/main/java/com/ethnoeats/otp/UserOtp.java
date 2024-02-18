package com.ethnoeats.otp;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;


@Data
@Entity
@Table(name = "user_otp")
public class UserOtp {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String emailOrPhoneNumber;
        private String otp;
        private LocalDateTime expirationTime;

        // Constructors, getters, and setters

        public UserOtp() {
        }
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
        public UserOtp(String emailOrPhoneNumber, String otp,String phoneNumber,String email, LocalDateTime expirationTime) {
            this.emailOrPhoneNumber = emailOrPhoneNumber;
            this.otp = otp;
            this.phoneNumber=phoneNumber;
            this.email=email;
            this.expirationTime = expirationTime;
        }

    public void setUserId(Long id) {
    }

    private Date calculateOtpExpiryDate() {
        // Create a Calendar instance and set the time to  15 minutes from now
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,  15);
        return calendar.getTime();
    }

    public void setExpiryDate(Date date) {
    }
    // Getters and setters for id, emailOrPhoneNumber, otp, and expirationTime
    }

