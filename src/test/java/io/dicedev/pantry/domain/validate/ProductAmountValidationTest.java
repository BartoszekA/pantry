package io.dicedev.pantry.domain.validate;

import io.dicedev.pantry.domain.dto.ProductDto;
import io.dicedev.pantry.domain.exception.PantryProductAmountException;
import io.dicedev.pantry.domain.exception.PantryProductNameException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductAmountValidationTest {

    private ProductValidator productAmountValidation = new ProductAmountValidation();

    @Test
    public void shouldThrowAnExceptionWhenProductAmountIsNull() {
        //Given
        ProductDto productDto = new ProductDto();

        //When
        var exception = assertThrows(PantryProductAmountException.class, () -> productAmountValidation.isValid(productDto));

        //Then
        assertTrue(exception.getMessage().equals("PANTRY_PRODUCT_AMOUNT_GREATER_THAN_ZERO"));
    }

    @Test
    public void shouldThrowAnExceptionWhenProductAmountIsNegative() {
        //Given
        ProductDto productDto = new ProductDto();
        productDto.setAmount(-1);

        //When
        var exception = assertThrows(PantryProductAmountException.class, () -> productAmountValidation.isValid(productDto));

        //Then
        assertTrue(exception.getMessage().equals("PANTRY_PRODUCT_AMOUNT_GREATER_THAN_ZERO"));
    }
}