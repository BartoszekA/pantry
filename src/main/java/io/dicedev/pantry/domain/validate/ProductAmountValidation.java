package io.dicedev.pantry.domain.validate;

import io.dicedev.pantry.domain.dto.ProductDto;
import io.dicedev.pantry.domain.enums.ProductValidatorEnum;
import io.dicedev.pantry.domain.exception.PantryProductAmountException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ProductAmountValidation implements ProductValidator {
    @Override
    public void isValid(ProductDto productDto) {
        var productAmount = productDto.getAmount();
        if(Objects.isNull(productAmount) || productAmount <= 0) {
            throw new PantryProductAmountException(ProductValidatorEnum.PANTRY_PRODUCT_AMOUNT_GREATER_THAN_ZERO.name());
        }
    }
}
