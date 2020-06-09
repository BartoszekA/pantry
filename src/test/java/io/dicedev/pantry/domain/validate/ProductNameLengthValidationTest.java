package io.dicedev.pantry.domain.validate;

import io.dicedev.pantry.domain.dto.ProductDto;
import io.dicedev.pantry.domain.exception.PantryProductNameLengthException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductNameLengthValidationTest {

    private ProductValidator productNameLengthValidator = new ProductNameLengthValidation();

    @Test
    public void shouldThrowAnExceptionWhenProductNameHas2Letters() {
        //Given
        ProductDto productDto = new ProductDto();
        productDto.setName("Ad");

        //When
        var exception = assertThrows(PantryProductNameLengthException.class, () -> productNameLengthValidator.isValid(productDto));

        //Then
        assertTrue(exception.getMessage().equals("PANTRY_PRODUCT_NAME_MIN_3_LETTERS"));
    }
}
