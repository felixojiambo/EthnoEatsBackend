package com.ethnoeats.review.service;

import com.ethnoeats.review.model.Review;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    List<Review> getAllReviews();

    Optional<Review> getReviewById(Long id);

    Review saveReview(Review review);

    void deleteReview(Long id);

    Review createReview(Review review);

    Review updateReview(Long id, Review review) throws ChangeSetPersister.NotFoundException;
}
