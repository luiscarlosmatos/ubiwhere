package com.ubiwhere.challenge.establishments.controller;

import com.ubiwhere.challenge.establishments.dtos.ReviewScoreDTO;
import org.springframework.http.ResponseEntity;

public interface EstablishmentController {

    ResponseEntity<ReviewScoreDTO> getEstablishmentReview(Long establishmentId);
}
