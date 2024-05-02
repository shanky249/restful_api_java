package com.crossvale.messageapp.controller;

import com.crossvale.messageapp.service.JWTService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/jwt")
@Api(tags = "JWT Management")
public class JWTController {
    private final JWTService jwtService;

    @ApiOperation("Build JWT")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "JWT token generated successfully"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/build")
    public ResponseEntity<String> buildJWT(
            @ApiParam(value = "Issuer of the JWT", required = false)
            @RequestParam(required = false) String issuer,
            @ApiParam(value = "Subject of the JWT", required = false)
            @RequestParam(required = false) String subject) {
        try {
            String token = jwtService.buildToken(issuer, subject);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error building JWT: " + e.getMessage());
        }
    }

    @ApiOperation("Validate JWT")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "JWT is valid"),
            @ApiResponse(code = 400, message = "JWT is invalid"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/validate")
    public ResponseEntity<String> validateJWT(
            @ApiParam(value = "JWT token in the Authorization header", required = true)
            @RequestHeader("Authorization") String token) {
        try {
            boolean isValid = jwtService.validateToken(token);
            if (isValid) {
                return ResponseEntity.ok("JWT is valid");
            } else {
                return ResponseEntity.badRequest().body("JWT is invalid");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error validating JWT: " + e.getMessage());
        }
    }
}
