package com.ubiwhere.challenge.reviews.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
@Table(name = "reviews")
public class Review extends DatedEntity {

    private static final int MAX_REVIEW_LENGTH = 200;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "review_establishment_id", nullable = false, updatable = false)
    private Long establishmentId;

    @Column(name = "review_score", nullable = false, updatable = false)
    @Max(5)
    @Min(1)
    private Long reviewScore;

    @Column(name = "review_details", nullable = false, updatable = false, length = MAX_REVIEW_LENGTH, columnDefinition = "TEXT")
    private String details;


    // constructors


    public Review() {

    }

    public Review(Long establishmentId, String details, Long score) {
        this.establishmentId = establishmentId;
        this.details = details;
        this.reviewScore = score;
    }

    public Review(Date createdAt, Date updatedAt, Long establishmentId, String details) {

        super(createdAt, updatedAt);
        this.establishmentId = establishmentId;
        this.details = details;
    }

    // Getters and Setters


    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Long getEstablishmentId() {

        return establishmentId;
    }

    public void setEstablishmentId(Long establishmentId) {

        this.establishmentId = establishmentId;
    }

    public String getDetails() {

        return details;
    }

    public void setDetails(String details) {

        this.details = details;
    }

    public Long getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(Long reviewScore) {
        this.reviewScore = reviewScore;
    }

    public String toJson() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return this.toString();
        }
    }
}
