package io.dicedev.pantry.mapper;

import io.dicedev.pantry.command.entity.LocationEntity;
import io.dicedev.pantry.domain.dto.LocationDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlaceMapper {

    PlaceMapper INSTANCE = Mappers.getMapper(PlaceMapper.class);

    LocationEntity locationDtoToLocationEntity(LocationDto locationDto);

    LocationDto locationEntityToLocationDto(LocationEntity locationEntity);
}
