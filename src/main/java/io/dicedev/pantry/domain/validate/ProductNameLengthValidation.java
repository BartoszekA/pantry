package io.dicedev.pantry.domain.validate;

import io.dicedev.pantry.domain.dto.ProductDto;
import io.dicedev.pantry.domain.enums.ProductValidatorEnum;
import io.dicedev.pantry.domain.exception.PantryProductNameLengthException;
import org.springframework.stereotype.Component;

@Component
public class ProductNameLengthValidation implements ProductValidator {
    @Override
    public void isValid(ProductDto productDto) {
        if (productDto.getName() == null || productDto.getName().length() < 3) {
            throw new PantryProductNameLengthException(ProductValidatorEnum.PANTRY_PRODUCT_NAME_MIN_3_LETTERS.name());
        }
    }
}
