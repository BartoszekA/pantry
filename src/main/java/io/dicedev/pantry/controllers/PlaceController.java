package io.dicedev.pantry.controllers;

import io.dicedev.pantry.domain.dto.PlaceDto;
import io.dicedev.pantry.domain.dto.PlacesDto;
import io.dicedev.pantry.domain.service.PlaceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/places")
@CrossOrigin
@AllArgsConstructor
public class PlaceController {

    private PlaceService placeService;

    @GetMapping
    public PlacesDto getPlaces() {
        return placeService.getPlaces();
    }

    @PostMapping
    public void addPlace(@RequestBody PlaceDto placeDto) {
        placeService.addPlace(placeDto);
    }

    @PutMapping
    public void renamePlace(@RequestBody PlaceDto placeDto) {
        placeService.renamePlace(placeDto);
    }
}
