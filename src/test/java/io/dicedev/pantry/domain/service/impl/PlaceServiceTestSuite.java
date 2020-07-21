package io.dicedev.pantry.domain.service.impl;

import io.dicedev.pantry.command.entity.PlaceEntity;
import io.dicedev.pantry.command.repository.PlaceRepository;
import io.dicedev.pantry.domain.dto.PlaceDto;
import io.dicedev.pantry.domain.dto.PlacesDto;
import io.dicedev.pantry.domain.service.PlaceService;
import io.dicedev.pantry.mapper.PlaceMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class PlaceServiceTestSuite {

    PlaceService placeService;

    @Mock
    private PlaceRepository placeRepository;

    @Mock
    private PlaceMapper placeMapper;

    @BeforeEach
    private void setUp() {
        placeService = new PlaceServiceImpl(placeRepository, placeMapper);
    }

    @Test
    public void shouldGetTwoPlacesAfterAddingTwoPlaces() {
        //Given
        PlaceDto placeDto1 = new PlaceDto();
        PlaceDto placeDto2 = new PlaceDto();

        //When
        placeService.addPlace(placeDto1);
        placeService.addPlace(placeDto2);

        //Then
        Mockito.verify(placeRepository, Mockito.times(2)).save(any());

    }

    @Test
    public void shouldGetAllPlaces() {
        //Given
        UUID placeId = UUID.randomUUID();
        String placeName = "place";
        PlaceEntity placeEntity = new PlaceEntity(placeId, placeName);
        PlaceDto placeDto = new PlaceDto(placeId, placeName);
        List<PlaceEntity> allEntities = List.of(placeEntity);
        Mockito.when(placeMapper.placeEntityToPlaceDto(placeEntity)).thenReturn(placeDto);
        Mockito.when(placeRepository.findAll()).thenReturn(allEntities);

        //When
        PlacesDto placesDto = placeService.getPlaces();

        //Then
        assertAll(
                () -> assertEquals(placeName, placesDto.getPlacesDto().get(0).getName()),
                () -> assertEquals(placeId, placesDto.getPlacesDto().get(0).getId())
        );
    }

    @Test
    public void shouldGetEmptyPlaceListWhenNoPlaceAdded() {
        //Given

        //When
        PlacesDto result = placeService.getPlaces();

        //Then
        assertEquals(0, result.getPlacesDto().size());
    }

    @Test
    public void shouldRenameExistingPlace() {
        //Given
        PlaceDto placeDto = new PlaceDto();
        placeService.addPlace(placeDto);

        //When
        placeService.renamePlace(placeDto);

        //Then
        Mockito.verify(placeRepository, Mockito.times(1)).findById(placeDto.getId());
        Mockito.verify(placeRepository, Mockito.times(1)).save(any());
    }

    @Test
    public void shouldNotRenameNotExistingProduct() {
        //Given
        PlaceDto placeDto = new PlaceDto();

        //When
        placeService.renamePlace(placeDto);

        //Then
        Mockito.verify(placeRepository, Mockito.times(1)).findById(placeDto.getId());
        Mockito.verify(placeRepository, Mockito.times(0)).save(any());
    }
}
