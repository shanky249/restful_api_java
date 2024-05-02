package com.crossvale.messageapp.controller;

import com.crossvale.messageapp.exception.RickAndMortyApiException;
import com.crossvale.messageapp.service.RickAndMortyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class RickAndMortyControllerTest {

    @Mock
    private RickAndMortyServiceImpl rickAndMortyService;

    @InjectMocks
    private RickAndMortyController rickAndMortyController;

    @BeforeEach
    void setUp() {
        rickAndMortyService = mock(RickAndMortyServiceImpl.class);
        rickAndMortyController = new RickAndMortyController(rickAndMortyService);
    }

    @Test
    void getCharacter_ValidId_Success() {
        // Arrange
        int characterId = 1;
        String characterInfo = "Character Info";
        when(rickAndMortyService.getCharacter(anyInt())).thenReturn(characterInfo);

        // Act
        ResponseEntity<String> response = rickAndMortyController.getCharacter(characterId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(characterInfo, response.getBody());
        verify(rickAndMortyService, times(1)).getCharacter(anyInt());
    }

    @Test
    void getCharacter_ServiceThrowsException_ExceptionHandled() {
        // Arrange
        int characterId = 1;
        String errorMessage = "Error Message";
        when(rickAndMortyService.getCharacter(anyInt())).thenThrow(new RickAndMortyApiException(HttpStatus.NOT_FOUND, errorMessage));

        // Act
        ResponseEntity<String> response = rickAndMortyController.getCharacter(characterId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Request to the external API failed with status 404 NOT_FOUND", response.getBody());
        verify(rickAndMortyService, times(1)).getCharacter(anyInt());
    }
}
