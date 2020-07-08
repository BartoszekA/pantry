package io.dicedev.pantry.mapper;

import io.dicedev.pantry.command.entity.LocationEntity;
import io.dicedev.pantry.domain.dto.LocationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlaceMapper {

    LocationEntity locationDtoToLocationEntity(LocationDto locationDto);

    LocationDto locationEntityToLocationDto(LocationEntity locationEntity);
}
