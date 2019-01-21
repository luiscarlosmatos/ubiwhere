package com.ubiwhere.challenge.reviews.controler;

import com.ubiwhere.challenge.reviews.entities.Review;
import com.ubiwhere.challenge.reviews.entities.ReviewScore;
import com.ubiwhere.challenge.reviews.persistence.ReviewDAO;
import com.ubiwhere.challenge.reviews.persistence.ReviewScoreDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class ReviewControllerImpl implements ReviewController {

    @Autowired
    private ReviewDAO reviewDao;

    @Autowired
    private ReviewScoreDAO reviewScoreDAO;

    @Override
    public ReviewScore saveReview(Review review, Long establishmentId) {

        review.setEstablishmentId(establishmentId);

        Review persistedReview = reviewDao.save(review);

        Optional<ReviewScore> scoreOptional = reviewScoreDAO.findById(establishmentId);

        if (scoreOptional.isPresent()) {
            log.info(String.format("ReviewScore found for establishment [%s]", establishmentId));
            scoreOptional.get().computeNewScoreUp(persistedReview);
            return reviewScoreDAO.save(scoreOptional.get());
        } else {
            log.info(String.format("ReviewScore not found for establishment [%s]. Create new one!", establishmentId));
            return reviewScoreDAO.save(new ReviewScore(persistedReview));
        }
    }

    @Override
    @Transactional
    public Optional<ReviewScore> delete(Review review) {

        Optional<ReviewScore> optionalReviewScore = reviewScoreDAO.findById(review.getEstablishmentId());

        if (optionalReviewScore.isPresent()) {
            ReviewScore score = optionalReviewScore.get();
            score.computeNewScoreDown(review);
            reviewDao.delete(review);

            if (score.getReviewsCount() == 0) {
                reviewScoreDAO.delete(score);
                return Optional.empty();
            } else {
                return Optional.of(score);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Review> findReviewById(Long reviewId) {
        return reviewDao.findById(reviewId);
    }

    @Override
    public ReviewScore updateReview(Review persistedReview, Review reviewRequest) {

        if (reviewRequest.getDetails() != null && !reviewRequest.getDetails().isEmpty()) {
            persistedReview.setDetails(reviewRequest.getDetails());
        }

        if (reviewRequest.getReviewScore() != null) {
            persistedReview.setReviewScore(reviewRequest.getReviewScore());
        }

        reviewDao.save(persistedReview);

        ReviewScore score = reviewScoreDAO.findById(persistedReview.getEstablishmentId()).get().computeNewScoreUp(persistedReview);

        reviewScoreDAO.save(score);

        return score;

    }

    @Override
    public Optional<ReviewScore> getScoreByEstablishmentId(Long establishmentId) {
        return reviewScoreDAO.findById(establishmentId);
    }
}
