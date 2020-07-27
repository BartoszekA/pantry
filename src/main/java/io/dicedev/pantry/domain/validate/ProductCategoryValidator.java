package io.dicedev.pantry.domain.validate;

import io.dicedev.pantry.domain.dto.ProductDto;
import io.dicedev.pantry.domain.enums.ProductValidatorEnum;
import io.dicedev.pantry.domain.exception.PantryProductCategoryException;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryValidator implements ProductValidator {
    @Override
    public void isValid(ProductDto productDto) {
        var productCategory = productDto.getCategory();
        if (productCategory == null) {
            throw new PantryProductCategoryException(ProductValidatorEnum.PANTRY_PRODUCT_CATEGORY_NOT_CHOSEN.name());
        }
    }
}
