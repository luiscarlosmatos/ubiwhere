package com.ubiwhere.challenge.reviews.dtos;

import org.springframework.http.HttpStatus;

public class ResponseDTO {

    private HttpStatus httpStatus;
    private String message;

    public ResponseDTO(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
