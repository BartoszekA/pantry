package io.dicedev.pantry.domain.validate;

import io.dicedev.pantry.domain.dto.ProductDto;
import io.dicedev.pantry.domain.exception.PantryProductNameException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductNameValidationTest {

    private final ProductValidator productNameValidation = new ProductNameValidation();

    @Test
    public void shouldValidateProductName() {
        //Given
        ProductDto productDto = new ProductDto();
        productDto.setName("product");

        //When
        var exception = assertThrows(PantryProductNameException.class, () -> productNameValidation.isValid(productDto));

        //Then
        assertEquals(exception.getMessage(), "PANTRY_PRODUCT_NAME_NO_SMALL_LETTER");
    }
}