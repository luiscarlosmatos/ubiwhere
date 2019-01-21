package com.ubiwhere.challenge.establishments.dtos;

public class ReviewScoreDTO {

    private Long establishmentId;
    private Double averageScore;
    private Long reviewsCount;

    //Constructors

    public ReviewScoreDTO() {
    }

    public ReviewScoreDTO(Long establishmentId, Double averageScore, Long reviewsCount) {
        this.establishmentId = establishmentId;
        this.averageScore = averageScore;
        this.reviewsCount = reviewsCount;
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
}
