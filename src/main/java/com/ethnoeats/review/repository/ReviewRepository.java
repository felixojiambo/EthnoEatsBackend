package com.ethnoeats.review.repository;
import com.ethnoeats.review.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
