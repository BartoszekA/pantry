package io.dicedev.pantry.mapper;

import io.dicedev.pantry.command.entity.ProductEntity;
import io.dicedev.pantry.domain.dto.ProductDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto productEntityToProductDto(ProductEntity productEntity);

    ProductEntity productDtoToProductEntity(ProductDto productDto);
}
