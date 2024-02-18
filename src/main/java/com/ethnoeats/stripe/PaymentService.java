package com.ethnoeats.stripe;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final EntityManager entityManager;

    @Autowired
    public PaymentService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public String createCharge(PaymentRequestDTO paymentRequest) {
        try {
            ChargeCreateParams params = ChargeCreateParams.builder()
                    .setAmount(paymentRequest.getAmount())
                    .setCurrency(paymentRequest.getCurrency())
                    .setDescription(paymentRequest.getDescription())
                    .setSource(paymentRequest.getToken())
                    .build();

            Charge charge = Charge.create(params);

            if (charge.getStatus().equals("succeeded")) {
                // Update the database with the payment details
                updateDatabaseWithPaymentDetails(charge.getId(), paymentRequest.getAmount(), paymentRequest.getCurrency());
                return "Charge succeeded!";
            } else {
                return "Charge failed: " + charge.getFailureMessage();
            }
        } catch (StripeException e) {
            // Log the exception and return an error message
            return "Error: " + e.getMessage();
        }
    }

    @Transactional
    public void updateDatabaseWithPaymentDetails(String chargeId, Long amount, String currency) {
        // Assuming you have a Payment entity with fields for chargeId, amount, and currency
        com.ethnoeats.stripe.Payment payment = new com.ethnoeats.stripe.Payment();
        payment.setChargeId(chargeId);
        payment.setAmount(amount);
        payment.setCurrency(currency);

        entityManager.persist(payment);
    }
}
