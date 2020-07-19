package io.dicedev.pantry.mapper;

import io.dicedev.pantry.command.entity.CategoryEntity;
import io.dicedev.pantry.domain.dto.CategoryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryEntity categoryDtoToCategoryEntity(CategoryDto categoryDto);

    CategoryDto categoryEntityToCategoryDto(CategoryEntity categoryEntity);
}
