package com.crossvale.messageapp.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JWTServiceImpl implements JWTService {
    private static final Logger LOGGER = LogManager.getLogger(JWTServiceImpl.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.issuer}")
    private String defaultIssuer;

    @Value("${jwt.subject}")
    private String defaultSubject;

    @Value("${jwt.validitySeconds}")
    private long validitySeconds;

    @Value("${jwt.algorithm}")
    private String algorithm;

    @Override
    public String buildToken(String issuer, String subject) {
        LOGGER.info("Building JWT token with issuer: {} and subject: {}", issuer, subject);
        try {
            if (issuer == null) issuer = defaultIssuer;
            if (subject == null) subject = defaultSubject;
            SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
            SignatureAlgorithm signatureAlgorithm = (algorithm != null) ?
                    SignatureAlgorithm.forName(algorithm) : SignatureAlgorithm.HS256;
            return Jwts.builder()
                    .setIssuer(issuer)
                    .setSubject(subject)
                    .signWith(key, signatureAlgorithm)
                    .compact();
        } catch (Exception e) {
            LOGGER.error("Error building JWT token: {}", e.getMessage(), e);
            throw new RuntimeException("Error building JWT token", e);
        }
    }

    @Override
    public boolean validateToken(String token) {
        LOGGER.info("Validating JWT token");
        try {
            String tokenWithoutPrefix = extractTokenWithoutPrefix(token);
            SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(tokenWithoutPrefix);

            Claims claims = claimsJws.getBody();

            // Check if the token has expired
            if (claims != null && claims.getExpiration() != null && claims.getExpiration().before(new Date())) {
                LOGGER.error("JWT token has expired");
                return false;
            }
            LOGGER.debug("JWT is valid");
            return true;
        } catch (Exception e) {
            LOGGER.error("Error validating JWT token: {}", e.getMessage(), e);
            return false;
        }
    }

    private String extractTokenWithoutPrefix(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return token;
    }

}
