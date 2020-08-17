package io.dicedev.pantry.domain.validate;

import io.dicedev.pantry.domain.dto.ProductDto;
import io.dicedev.pantry.domain.exception.PantryProductCategoryException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductCategoryValidationTest {

    private final ProductCategoryValidator productCategoryValidator = new ProductCategoryValidator();

    @Test
    public void shouldThrowExceptionIfCategoryIsNull() {
        //Given
        ProductDto productDto = new ProductDto();

        //When
        var exception = assertThrows(PantryProductCategoryException.class, () -> productCategoryValidator.isValid(productDto));

        //Then
        assertEquals(exception.getMessage(), "PANTRY_PRODUCT_CATEGORY_NOT_CHOSEN");
    }
}
