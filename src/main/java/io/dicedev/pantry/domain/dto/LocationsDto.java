package io.dicedev.pantry.domain.dto;

import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode
public class LocationsDto {
    private List<LocationDto> locationsDto;

    public List<LocationDto> getLocationsDto() {
        return locationsDto;
    }

    public void setLocationsDto(List<LocationDto> locationsDto) {
        this.locationsDto = locationsDto;
    }

    @Override
    public String toString() {
        return "LocationsDto{" +
                "locations=" + locationsDto +
                "}";
    }
}
