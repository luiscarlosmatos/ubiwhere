package com.ubiwhere.challenge.establishments.restapi;


import com.ubiwhere.challenge.establishments.controller.EstablishmentController;
import com.ubiwhere.challenge.establishments.dtos.ReviewScoreDTO;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestApiImpl implements RestApi {

    private Logger log = LoggerFactory.getLogger(RestApiImpl.class);

    @Autowired
    private Environment env;

    @Autowired
    private EstablishmentController establishmentController;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public ResponseEntity getEstablishmentScore(String establishmentId) {

        String url = env.getProperty("establishments.api.url") + establishmentId;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Length", "2225");
        headers.set("x-api-version", "2");
        headers.set("Accept", "application/json");

        ResponseEntity<String> establishmentRequest = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>("parameters", headers), String.class);

        if (!establishmentRequest.getStatusCode().equals(HttpStatus.OK) || establishmentRequest.getBody() == null) {
            log.error(String.format("Fail to get establishment [%s]", establishmentId));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("Fail to get establishment [%s]", establishmentId));
        }
        JSONObject jsonObject;

        ResponseEntity<ReviewScoreDTO> establishmentReview = establishmentController.getEstablishmentReview(Long.parseLong(establishmentId));

        if (!establishmentReview.getStatusCode().equals(HttpStatus.OK) || establishmentReview.getBody() == null) {
            log.error(String.format("Fail to get reviewScore for establishment [%s]", establishmentId));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("\"Fail to get reviewScore for establishment [%s]\"", establishmentId));
        }

        try {
            jsonObject = new JSONObject(establishmentRequest.getBody());
            jsonObject.put("AverageReviewScore", establishmentReview.getBody().getAverageScore());
        } catch (JSONException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fail to parse Establishment info");
        }

        return ResponseEntity.ok(jsonObject.toString());
    }
}
