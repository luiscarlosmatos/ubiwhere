package com.ubiwhere.challenge.reviews.controler;

import com.ubiwhere.challenge.reviews.entities.Review;
import com.ubiwhere.challenge.reviews.entities.ReviewScore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public interface ReviewController {

    Logger log = LoggerFactory.getLogger(ReviewController.class);

    ReviewScore saveReview(Review review, Long establishmentId);

    Optional<ReviewScore> delete(Review review);

    Optional<Review> findReviewById(Long reviewId);

    ReviewScore updateReview(Review persistedReview, Review reviewRequest);

    Optional<ReviewScore> getScoreByEstablishmentId(Long establishmentId);
}
