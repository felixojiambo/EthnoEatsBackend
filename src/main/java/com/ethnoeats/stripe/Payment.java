package com.ethnoeats.stripe;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Setter methods
    @Setter
    private String chargeId;
    @Setter
    private Long amount;
    @Setter
    private String currency;

}
