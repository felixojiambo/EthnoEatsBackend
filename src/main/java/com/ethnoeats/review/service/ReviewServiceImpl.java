package com.ethnoeats.review.service;
import com.ethnoeats.review.model.Review;
import com.ethnoeats.review.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    public Review createReview(Review review) {
        // Perform validation checks
        validateReview(review);

        // Save the review
        return reviewRepository.save(review);
    }

    private void validateReview(Review review) {
        // Example validation: check if rating is within valid range
        int rating = review.getRating();
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }
        // Add more validation checks as needed
    }
    public Review updateReview(Long id, Review review) throws ChangeSetPersister.NotFoundException {
        // Find the existing review by ID
        Review existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        // Update the existing review with the new data
        existingReview.setUser(review.getUser());
        existingReview.setRestaurant(review.getRestaurant());
        existingReview.setRating(review.getRating());
        existingReview.setComment(review.getComment());
        existingReview.setReviewDate(review.getReviewDate());

        // Save the updated review
        return reviewRepository.save(existingReview);
    }
}
