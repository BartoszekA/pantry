package io.dicedev.pantry.domain.validate;

import io.dicedev.pantry.domain.dto.ProductDto;

public interface ProductValidator {
    void isValid(ProductDto productDto);
}
