package com.ubiwhere.challenge.reviews.persistence;

import com.ubiwhere.challenge.reviews.entities.ReviewScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewScoreDAO extends JpaRepository<ReviewScore, Long> {
}
