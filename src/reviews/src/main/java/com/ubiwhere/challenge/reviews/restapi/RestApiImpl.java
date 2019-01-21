package com.ubiwhere.challenge.reviews.restapi;

import com.ubiwhere.challenge.reviews.controler.ReviewController;
import com.ubiwhere.challenge.reviews.dtos.ResponseDTO;
import com.ubiwhere.challenge.reviews.entities.Review;
import com.ubiwhere.challenge.reviews.entities.ReviewScore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class RestApiImpl implements RestApi {

    private Logger log = LoggerFactory.getLogger(RestApi.class);

    @Autowired
    private ReviewController reviewController;

    @Override
    public ResponseEntity createReview(@Valid Review review, Long establishmentId) {
        log.info(String.format("saving review [%s] for establishment [%s]", review.toJson(), establishmentId));
        ReviewScore score = reviewController.saveReview(review, establishmentId);
        return ResponseEntity.ok(score);
    }

    @Override
    public ResponseEntity deleteReview(Long reviewId) {
        log.info(String.format("Deleting review with id [%s]", reviewId));
        Optional<ReviewScore> returnScore;

        Optional<Review> review = reviewController.findReviewById(reviewId);

        if (review.isPresent()) {
            returnScore = reviewController.delete(review.get());
            if (returnScore.isPresent()) {
                return ResponseEntity.ok(returnScore.get());
            } else {
                return ResponseEntity.ok(
                        new ResponseDTO(HttpStatus.OK, String.format("No reviews for establishment with id [%s]", review.get().getEstablishmentId()))
                );
            }
        } else {
            log.error(String.format("Review with id [%s] not found", reviewId));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseDTO(HttpStatus.NOT_FOUND, String.format("Review with id [%s] not found", reviewId))
            );
        }
    }

    @Override
    public ResponseEntity getReview(Long reviewId) {

        log.info(String.format("Getting review with id [%s]", reviewId));

        Optional<Review> review = reviewController.findReviewById(reviewId);

        if (review.isPresent()) {
            return ResponseEntity.ok(review.get());
        } else {
            log.error(String.format("Review with id [%s] not found", reviewId));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseDTO(HttpStatus.NOT_FOUND, String.format("review with id [%s] not found", reviewId))
            );
        }
    }

    @Override
    public ResponseEntity updateReview(@Valid Review reviewRequest, Long reviewId) {

        log.info(String.format("updating Review [%s] with [%s]", reviewId, reviewRequest));

        Optional<Review> review = reviewController.findReviewById(reviewId);

        if (review.isPresent()) {
            return ResponseEntity.ok(reviewController.updateReview(review.get(), reviewRequest));
        } else {
            log.info(String.format("Review with id [%s] not found.", reviewId));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseDTO(HttpStatus.NOT_FOUND, String.format("review with id [%s] not found", reviewId))
            );
        }
    }

    @Override
    public ResponseEntity getReviewScoreByEstablishmentId(Long establishmentId) {

        log.info(String.format("Getting Review score for establishment [%s]", establishmentId));
        Optional<ReviewScore> score = reviewController.getScoreByEstablishmentId(establishmentId);

        if (score.isPresent()) {
            return ResponseEntity.ok(score);
        } else {
            log.error(String.format("Review score for establishment [%s] not found", establishmentId));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseDTO(HttpStatus.NOT_FOUND, String.format("Establishment [%s] has no reviews yet", establishmentId))
            );
        }
    }

}
