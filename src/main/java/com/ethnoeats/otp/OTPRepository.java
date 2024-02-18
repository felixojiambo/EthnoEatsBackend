package com.ethnoeats.otp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OTPRepository extends JpaRepository<UserOtp, Long> {
    UserOtp findByEmailOrPhoneNumber(String email, String phoneNumber);
    //UserOtp findByEmailOrPhoneNumber(String emailOrPhoneNumber);
    List<UserOtp> findByEmail(String email);
    List<UserOtp> findByPhoneNumber(String phoneNumber);
    // You can add more custom queries if needed
}
