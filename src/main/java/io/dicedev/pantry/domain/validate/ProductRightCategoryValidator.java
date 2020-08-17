package io.dicedev.pantry.domain.validate;

import io.dicedev.pantry.command.entity.ProductEntity;
import io.dicedev.pantry.command.repository.ProductRepository;
import io.dicedev.pantry.domain.dto.ProductDto;
import io.dicedev.pantry.domain.enums.ProductValidatorEnum;
import io.dicedev.pantry.domain.exception.PantryProductRightCategoryException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Order
@AllArgsConstructor
public class ProductRightCategoryValidator implements ProductValidator {

    private final ProductRepository productRepository;

    @Override
    public void isValid(ProductDto productDto) {
        String productName = productDto.getName().toLowerCase();
        String name = productName.substring(0, 1).toUpperCase() + productName.substring(1);
        Optional<ProductEntity> entity =
                Optional.ofNullable(productRepository.findByName(name)).orElseThrow();
        if (entity.isPresent())  {
            ProductEntity productEntity = entity.get();
            assertCategoryId(productDto, productEntity);
        }
    }

    private void assertCategoryId(ProductDto productDto, ProductEntity productEntity) {
        var result = productDto.getCategory().getId().equals(productEntity.getCategory().getId());
        if (!result) {
            throw new PantryProductRightCategoryException(ProductValidatorEnum.PANTRY_PRODUCT_WRONG_CATEGORY.name());
        }
    }
}
