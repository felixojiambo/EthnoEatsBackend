package com.ethnoeats.auth;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
    @Service
    public class SMSServiceImpl implements SMSService {

        @Value("${twilio.account.sid}")
        private String twilioAccountSid;

        @Value("${twilio.auth.token}")
        private String twilioAuthToken;

        @Value("${twilio.from.phone.number}")
        private String twilioFromPhoneNumber;

        @Override
        public void sendOtpSms(String to, String otp) {
            Twilio.init(twilioAccountSid, twilioAuthToken);

            Message message = Message.creator(
                            new PhoneNumber(to), // to
                            new PhoneNumber(twilioFromPhoneNumber),
                            "Your OTP is: " + otp)
                    .create();


            System.out.println(message.getSid());
        }
    }

