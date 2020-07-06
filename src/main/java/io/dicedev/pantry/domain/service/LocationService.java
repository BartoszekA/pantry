package io.dicedev.pantry.domain.service;

import io.dicedev.pantry.domain.dto.LocationDto;
import io.dicedev.pantry.domain.dto.LocationsDto;

public interface LocationService {
    LocationsDto getLocations();

    void addLocation(LocationDto locationDto);

    void renameLocation(LocationDto locationDto);
}
