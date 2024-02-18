package com.ethnoeats.auth;


import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import io.jsonwebtoken.io.IOException;
import org.springframework.stereotype.Service;


import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Value;


@Service
public class EmailServiceImpl implements EmailService {

    @Value("${sendgrid.api.key}")
    private String sendGridApiKey;

    @Value("${sendgrid.from.email}")
    private String sendGridFromEmail;

    @Override
    public void sendOtpEmail(String to, String otp) {
        Email from = new Email(sendGridFromEmail);
        Email toEmail = new Email(to);
        Content content = new Content("text/plain", "Your OTP is: " + otp);
        Mail mail = new Mail(from, "OTP Verification", toEmail, content);

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

        } catch (IOException ignored) {

        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }
}
