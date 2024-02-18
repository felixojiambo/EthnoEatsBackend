package com.ethnoeats.review.model;
import com.ethnoeats.restaurant.model.Restaurant;
import com.ethnoeats.user.model.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    private Restaurant restaurant;
    @Column(nullable = false)
    private int rating;
    @Column(nullable = false)
    private String comment;
        @Column(name = "review_date")
    private LocalDateTime reviewDate;
}
