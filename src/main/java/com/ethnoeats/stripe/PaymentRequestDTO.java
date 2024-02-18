package com.ethnoeats.stripe;

public class PaymentRequestDTO {

    private String token;
    private Long amount; // Amount in cents
    private String currency;
    private String description;

    // Default constructor
    public PaymentRequestDTO() {
    }

    // Constructor with all fields
    public PaymentRequestDTO(String token, Long amount, String currency, String description) {
        this.token = token;
        this.amount = amount;
        this.currency = currency;
        this.description = description;
    }

    // Getters and setters for all fields
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
