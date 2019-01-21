package com.ubiwhere.challenge.establishments.controller;

import com.ubiwhere.challenge.establishments.dtos.ReviewScoreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Repository
public class EstablishmentControllerImpl implements EstablishmentController {


    @Autowired
    private Environment env;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public ResponseEntity<ReviewScoreDTO> getEstablishmentReview(Long establishmentId) {

        String url = env.getProperty("reviewsScore.api.url") + establishmentId;

        try {
            return restTemplate.exchange(url, HttpMethod.GET, null, ReviewScoreDTO.class);
        } catch (RestClientException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
