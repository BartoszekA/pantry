package io.dicedev.pantry.domain.service.impl;

import io.dicedev.pantry.command.entity.PlaceEntity;
import io.dicedev.pantry.command.repository.PlaceRepository;
import io.dicedev.pantry.domain.dto.PlaceDto;
import io.dicedev.pantry.domain.dto.PlacesDto;
import io.dicedev.pantry.domain.service.PlaceService;
import io.dicedev.pantry.mapper.PlaceMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class PlaceServiceImpl implements PlaceService {
    private final PlaceRepository placeRepository;

    private PlaceMapper placeMapper;

    @Override
    public PlacesDto getPlaces() {
        log.info("Getting all possible places");
        PlacesDto placesDto = new PlacesDto();
        placesDto.setPlacesDto(new ArrayList<>());
        placeRepository.findAll()
                .forEach(placeEntity -> placeMapper.placeEntityToPlaceDto(placeEntity));
        log.info("Found following places: {} ", placesDto.toString());
        return placesDto;
    }

    @Override
    public void addPlace(PlaceDto placeDto) {
        log.info("Adding location {}", placeDto);
        UUID placeId = placeDto.getId();
        String placeName = placeDto.getName();
        PlaceEntity placeEntity = placeMapper.placeDtoToPlaceEntity(placeDto);
        placeRepository.save(placeEntity);
        log.info("Location {} added.", placeId);
    }

    @Override
    public void renamePlace(PlaceDto placeDto) {
        log.info("Renaming place {}", placeDto);
        UUID placeId = placeDto.getId();
        Optional<PlaceEntity> place = placeRepository.findById(placeId);
        if (place.isPresent()) {
            PlaceEntity placeEntity = place.get();
            placeEntity.setName(placeDto.getName());
            placeRepository.save(placeEntity);
        }
    }
}