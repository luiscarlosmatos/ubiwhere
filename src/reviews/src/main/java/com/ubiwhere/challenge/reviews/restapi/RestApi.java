package com.ubiwhere.challenge.reviews.restapi;

import com.ubiwhere.challenge.reviews.entities.Review;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Api(value = "/", description = "Review Service API", produces = "application/json")
public interface RestApi {

    @PostMapping("/review/{establishmentId}")
    @ApiOperation(value = "Creates a review for a Establishment", produces = "application/json")
    ResponseEntity createReview(@Valid @RequestBody Review review, @PathVariable(value = "establishmentId") Long establishmentId);

    @DeleteMapping("/review/{reviewId}")
    @ApiOperation(value = "Delete a review by ID", produces = "application/json")
    ResponseEntity deleteReview(@PathVariable(value = "reviewId") Long reviewId);

    @GetMapping("/review/{reviewId}")
    @ApiOperation(value = "Get a review by ID", produces = "application/json")
    ResponseEntity getReview(@PathVariable(value = "reviewId") Long establishmentId);

    @PutMapping("/review/{reviewId}")
    @ApiOperation(value = "Update a review by ID", produces = "application/json")
    ResponseEntity updateReview(@Valid @RequestBody Review reviewRequest, @PathVariable(value = "reviewId") Long reviewId);

    @GetMapping("/score/{establishmentId}")
    @ApiOperation(value = "Get ReviewScore for a establishment", produces = "application/json")
    ResponseEntity getReviewScoreByEstablishmentId(
            @PathVariable(value = "establishmentId")
                    Long establishmentId);


}
