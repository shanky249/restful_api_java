package com.crossvale.messageapp.service;

import com.crossvale.messageapp.exception.RickAndMortyApiException;

import java.util.List;

public interface RickAndMortyService {
    public String getCharacter(int characterId) throws RickAndMortyApiException;
}
