package io.dicedev.pantry.domain.service;

import io.dicedev.pantry.domain.dto.PlaceDto;
import io.dicedev.pantry.domain.dto.PlacesDto;

public interface PlaceService {
    PlacesDto getPlaces();

    void addPlace(PlaceDto placeDto);

    void renamePlace(PlaceDto placeDto);
}
