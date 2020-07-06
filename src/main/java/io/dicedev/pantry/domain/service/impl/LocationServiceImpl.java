package io.dicedev.pantry.domain.service.impl;

import io.dicedev.pantry.command.entity.LocationEntity;
import io.dicedev.pantry.command.repository.LocationRepository;
import io.dicedev.pantry.domain.dto.LocationDto;
import io.dicedev.pantry.domain.dto.LocationsDto;
import io.dicedev.pantry.domain.service.LocationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;

    @Override
    public LocationsDto getLocations() {
        log.info("Getting all possible locations");
        LocationsDto locationsDto = new LocationsDto();
        locationsDto.setLocationsDto(new ArrayList<>());
        locationRepository.findAll()
                .forEach(locationEntity -> {
                    LocationDto locationDto = LocationDto.builder()
                            .id(locationEntity.getId())
                            .name(locationEntity.getName())
                            .build();
                    locationsDto.getLocationsDto().add(locationDto);
                });
        log.info("Found following locations: {} ", locationsDto.toString());
        return locationsDto;
    }

    @Override
    public void addLocation(LocationDto locationDto) {
        log.info("Adding location {}", locationDto);
        UUID locationId = locationDto.getId();
        String locationName = locationDto.getName();
        LocationEntity locationEntity = LocationEntity.builder()
                .id(locationId)
                .name(locationName)
                .build();
        locationRepository.save(locationEntity);
        log.info("Location {} added.", locationId);
    }

    @Override
    public void renameLocation(LocationDto locationDto) {
        log.info("Renaming location {}", locationDto);
        UUID locationId = locationDto.getId();
        Optional<LocationEntity> location = locationRepository.findById(locationId);
        if (location.isPresent()) {
            LocationEntity locationEntity = location.get();
            locationEntity.setName(locationDto.getName());
            locationRepository.save(locationEntity);
        }
    }
}
