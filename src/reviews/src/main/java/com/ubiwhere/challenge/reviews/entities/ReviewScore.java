package com.ubiwhere.challenge.reviews.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "review_score")
public class ReviewScore {

    @Id
    @Column(name = "establishmentId", nullable = false, updatable = false)
    private Long establishmentId;

    @Column(name = "average_score", nullable = false)
    private Double averageScore;

    @Column(name = "reviews_count", nullable = false)
    private Long reviewsCount;


    // Constructors


    public ReviewScore() {
    }

    public ReviewScore(Long establishmentId, Double averageScore, Long reviewsCount) {
        this.establishmentId = establishmentId;
        this.averageScore = averageScore;
        this.reviewsCount = reviewsCount;
    }

    public ReviewScore(Review review) {
        this.establishmentId = review.getEstablishmentId();
        this.averageScore = Double.valueOf(review.getReviewScore());
        this.reviewsCount = 1L;
    }

    // Getters and Setters


    public Long getEstablishmentId() {
        return establishmentId;
    }

    public void setEstablishmentId(Long establishmentId) {
        this.establishmentId = establishmentId;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }

    public Long getReviewsCount() {
        return reviewsCount;
    }

    public void setReviewsCount(Long reviewsCount) {
        this.reviewsCount = reviewsCount;
    }

    public ReviewScore computeNewScoreUp(Review review) {

        this.averageScore = ((this.averageScore * this.reviewsCount) + review.getReviewScore()) / (this.reviewsCount + 1);
        this.reviewsCount++;
        return null;
    }

    public void computeNewScoreDown(Review review) {
        if (this.reviewsCount == 1) {
            this.averageScore = 0.0;
            this.reviewsCount = 0L;

        } else {
            this.averageScore = ((this.averageScore * this.reviewsCount) - review.getReviewScore()) / (this.reviewsCount - 1);
            this.reviewsCount--;
        }
    }
}
