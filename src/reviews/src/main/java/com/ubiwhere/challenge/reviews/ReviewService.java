package com.ubiwhere.challenge.reviews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ReviewService {

    public static void main(String[] args) {

        SpringApplication.run(ReviewService.class, args);
    }

}

