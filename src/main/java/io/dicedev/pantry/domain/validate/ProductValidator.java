package io.dicedev.pantry.domain.validate;

import io.dicedev.pantry.domain.dto.ProductDto;

import java.security.InvalidKeyException;

public interface ProductValidator {
    void isValid(ProductDto productDto);
}
