package com.ubiwhere.challenge.establishments.restapi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Api(value = "/", description = "Establishment Service API", produces = "application/json")
public interface RestApi {

    @GetMapping(path = "/establishments/{establishmentId}", produces = "application/json")

    @ApiOperation(value = "Gets the establishment information along with the review's average score", produces = "application/json")
    ResponseEntity getEstablishmentScore(@PathVariable String establishmentId);
}
