package com.ethnoeats.stripe;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Value("${twilio.accountSid}")
    private String accountSid;

    @Value("${twilio.authToken}")
    private String authToken;

    @Value("${twilio.fromPhoneNumber}")
    private String fromPhoneNumber;

    public void sendSms(String toPhoneNumber, String messageBody) {
        Twilio.init(accountSid, authToken);

        Message message = Message.creator(
                        new PhoneNumber(toPhoneNumber), // to
                        new PhoneNumber(fromPhoneNumber), // from
                        messageBody)
                .create();

        System.out.println("Message sent: " + message.getSid());
    }
}
