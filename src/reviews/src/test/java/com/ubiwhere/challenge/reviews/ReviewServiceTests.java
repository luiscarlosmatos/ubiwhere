package com.ubiwhere.challenge.reviews;


import com.ubiwhere.challenge.reviews.entities.Review;
import com.ubiwhere.challenge.reviews.entities.ReviewScore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ReviewServiceTests {

    @Autowired
    TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

//	@Test
//	public void contextLoads() {
//	}

    @Test
    public void createReviewTest() {

        Long establishmentId = 1L;

        Review review = new Review();
        review.setReviewScore(5L);
        review.setDetails("Teste Review");

        String postUrl = getRootUrl() + "/review/" + establishmentId;

        ResponseEntity<ReviewScore> requestResponse = testRestTemplate.postForEntity(postUrl, review, ReviewScore.class);
        assertNotNull(requestResponse);
        assertNotNull(requestResponse.getBody());

        ReviewScore reviewScore = requestResponse.getBody();

        assertEquals(1L, (long) reviewScore.getReviewsCount());
        assertEquals(5.0, reviewScore.getAverageScore(), 0.0);


        Review review2 = new Review();
        review2.setReviewScore(1L);
        review2.setDetails("Teste Review");

        ResponseEntity<ReviewScore> requestResponse2 = testRestTemplate.postForEntity(postUrl, review2, ReviewScore.class);
        assertNotNull(requestResponse2);
        assertNotNull(requestResponse2.getBody());

        ReviewScore reviewScore2 = requestResponse2.getBody();

        assertEquals(2L, (long) reviewScore2.getReviewsCount());
        assertEquals(3.0, reviewScore2.getAverageScore(), 0.0);

    }

}

