package com.crossvale.messageapp.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RickAndMortyApiException extends RuntimeException {

    private final HttpStatus statusCode;

    public RickAndMortyApiException(HttpStatus statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public String getStatusText() {
        return statusCode.getReasonPhrase();
    }
}
