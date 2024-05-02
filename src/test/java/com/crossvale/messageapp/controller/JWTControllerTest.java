package com.crossvale.messageapp.controller;

import com.crossvale.messageapp.service.JWTService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JWTController.class)
public class JWTControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JWTService jwtService;

    @Test
    public void testBuildJWT() throws Exception {
        mockMvc.perform(get("/jwt/build")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testValidateJWT_InvalidToken() throws Exception {
        mockMvc.perform(get("/jwt/validate")
                        .header("Authorization", "Bearer invalid_jwt_token")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testValidateJWT_ValidToken() throws Exception {
        mockMvc.perform(get("/jwt/validate")
                        .header("Authorization", "Bearer valid_jwt_token")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }
}
