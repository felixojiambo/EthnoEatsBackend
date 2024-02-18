package com.ethnoeats.stripe;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import io.jsonwebtoken.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${sendgrid.apiKey}")
    private String apiKey;

    public void sendEmail(String toEmail, String subject, String messageBody) {
        Email from = new Email("noreply@yourdomain.com"); // Replace with your "from" email address
        Email to = new Email(toEmail);
        Content content = new Content("text/plain", messageBody);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println("Email sent: " + response.getStatusCode());
        } catch (IOException | java.io.IOException e) {
            // Log the error and handle it appropriately
            e.printStackTrace();
        }
    }
}
