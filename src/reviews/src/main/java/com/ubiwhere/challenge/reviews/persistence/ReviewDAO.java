package com.ubiwhere.challenge.reviews.persistence;

import com.ubiwhere.challenge.reviews.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewDAO extends JpaRepository<Review, Long> {
}