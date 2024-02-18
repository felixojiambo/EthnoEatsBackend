package com.ethnoeats.stripe;

// ... other imports ...

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook")
public class StripeWebhookController {

    private final EmailService emailService;
    private final PaymentService paymentService;
    private final SmsService smsService;

    @Autowired
    public StripeWebhookController(EmailService emailService, PaymentService paymentService, SmsService smsService) {
        this.emailService = emailService;
        this.paymentService = paymentService;
        this.smsService = smsService;
    }

    // ... handleStripeWebhook method ...

    private void sendConfirmationEmail(String customerEmail, Long amount, String currency) {
        String subject = "Payment Confirmation";
        String messageBody = "Your payment of " + amount + " " + currency + " has been successfully processed.";
        emailService.sendEmail(customerEmail, subject, messageBody);
    }

    private void updateDatabaseWithPaymentDetails(String customerEmail, Long amount, String currency) {
        paymentService.updateDatabaseWithPaymentDetails(customerEmail, amount, currency);
    }

    private void sendSmsConfirmation(String customerPhoneNumber, Long amount, String currency) {
        String messageBody = "Your payment of " + amount + " " + currency + " has been successfully processed.";
        smsService.sendSms(customerPhoneNumber, messageBody);
    }
}
