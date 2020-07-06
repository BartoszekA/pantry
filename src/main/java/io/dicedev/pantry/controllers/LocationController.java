package io.dicedev.pantry.controllers;

import io.dicedev.pantry.domain.dto.LocationDto;
import io.dicedev.pantry.domain.dto.LocationsDto;
import io.dicedev.pantry.domain.service.LocationService;
import org.springframework.web.bind.annotation.*;

@RestController
public class LocationController {

    private LocationService locationService;

    @GetMapping
    public LocationsDto getLocations() {
        return locationService.getLocations();
    }

    @PostMapping
    public void addLocation(@RequestBody LocationDto locationDto) {
        locationService.addLocation(locationDto);
    }

    @PutMapping
    public void renameLocation(@RequestBody LocationDto locationDto) {
        locationService.renameLocation(locationDto);
    }
}
