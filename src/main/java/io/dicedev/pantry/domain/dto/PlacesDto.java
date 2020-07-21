package io.dicedev.pantry.domain.dto;

import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode
public class PlacesDto {
    private List<PlaceDto> placesDto;

    public List<PlaceDto> getPlacesDto() {
        return placesDto;
    }

    public void setPlacesDto(List<PlaceDto> placesDto) {
        this.placesDto = placesDto;
    }

    @Override
    public String toString() {
        return "PlacesDto{" +
                "places=" + placesDto +
                "}";
    }
}
