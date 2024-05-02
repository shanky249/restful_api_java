package com.crossvale.messageapp.service;

public interface JWTService {
    String buildToken(String issuer, String subject);
    boolean validateToken(String token);
}
