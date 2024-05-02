package com.crossvale.messageapp.controller;

import com.crossvale.messageapp.exception.RickAndMortyApiException;
import com.crossvale.messageapp.service.RickAndMortyServiceImpl;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/richandmorty")
@Api(tags = "Rick and Morty API")
public class RickAndMortyController {

    private final RickAndMortyServiceImpl rickAndMortyService;
    private static final Logger LOGGER = LogManager.getLogger(RickAndMortyController.class);

    @ApiOperation("Get character by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Character information retrieved successfully"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Character not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/character/{characterId}")
    public ResponseEntity<String> getCharacter(
            @ApiParam(value = "ID of the character to retrieve", required = true)
            @PathVariable("characterId") int characterId) {
        LOGGER.info("Fetching character with ID: {}", characterId);
        try {
            String characterInfo = rickAndMortyService.getCharacter(characterId);
            LOGGER.info("Character fetched successfully");
            return ResponseEntity.ok(characterInfo);
        } catch (RickAndMortyApiException e) {
            LOGGER.error("Error fetching character with ID {}: {}", characterId, e.getMessage());
            return ResponseEntity.status(e.getStatusCode())
                    .body("Request to the external API failed with status " + e.getStatusCode());
        }
    }
}
