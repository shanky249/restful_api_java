package com.crossvale.messageapp.service;

import com.crossvale.messageapp.exception.RickAndMortyApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class RickAndMortyServiceImpl implements RickAndMortyService{

    @Value("${rickandmorty.api.url}")
    private String rickAndMortyApiUrl;

    private final RestTemplate restTemplate;

    public RickAndMortyServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getCharacter(int characterId) throws RickAndMortyApiException {
        try {
            String url = rickAndMortyApiUrl + characterId;
            return restTemplate.getForObject(url, String.class);
        } catch (HttpClientErrorException ex) {
            throw new RickAndMortyApiException(ex.getStatusCode(), ex.getStatusText());
        }
    }
}
