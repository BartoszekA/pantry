package io.dicedev.pantry.mapper;

import io.dicedev.pantry.command.entity.PlaceEntity;
import io.dicedev.pantry.domain.dto.PlaceDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlaceMapper {

    PlaceEntity placeDtoToPlaceEntity(PlaceDto placeDto);

    PlaceDto placeEntityToPlaceDto(PlaceEntity placeEntity);
}
