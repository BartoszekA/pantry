package io.dicedev.pantry.domain.validate;

import io.dicedev.pantry.command.entity.CategoryEntity;
import io.dicedev.pantry.command.entity.ProductEntity;
import io.dicedev.pantry.command.repository.ProductRepository;
import io.dicedev.pantry.domain.dto.CategoryDto;
import io.dicedev.pantry.domain.dto.ProductDto;
import io.dicedev.pantry.domain.exception.PantryProductRightCategoryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductRightCategoryValidationTest {

    private ProductValidator productRightCategoryValidator;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        productRightCategoryValidator = new ProductRightCategoryValidator(productRepository);
    }

    @Test
    public void shouldThrowAnExceptionWhileAddingProductWithSimilarNameAndOtherCategory() {
        //Given
        UUID productId = UUID.randomUUID();
        String productName = "Product";
        Integer productAmount = 1;
        UUID categoryId = UUID.randomUUID();
        UUID entityCategoryId = UUID.randomUUID();
        String categoryName = "Category";
        CategoryDto categoryDto = new CategoryDto(categoryId, categoryName);
        CategoryEntity categoryEntity = new CategoryEntity(entityCategoryId, categoryName);
        ProductDto productDto = ProductDto.builder()
                .id(productId)
                .name(productName)
                .amount(productAmount)
                .category(categoryDto)
                .deleted(false)
                .build();
        ProductEntity productEntity = ProductEntity.builder()
                .id(productId)
                .name(productName)
                .amount(productAmount)
                .category(categoryEntity)
                .deleted(false)
                .build();

        Mockito.when(productRepository.findByName(productName)).thenReturn(Optional.of(productEntity));

        //When
        var exception = assertThrows(PantryProductRightCategoryException.class,
                () -> productRightCategoryValidator.isValid(productDto));

        //Then
        assertEquals(exception.getMessage(), "PANTRY_PRODUCT_WRONG_CATEGORY");

    }

    @Test
    public void shouldValidateProductWithSimilarNameAndSameCategory() {
        //Given
        UUID productId = UUID.randomUUID();
        String productName = "PrOdUcT";
        String entityName = "Product";
        Integer productAmount = 1;
        UUID categoryId = UUID.randomUUID();
        String categoryName = "Category";
        CategoryDto categoryDto = new CategoryDto(categoryId, categoryName);
        CategoryEntity categoryEntity = new CategoryEntity(categoryId, categoryName);
        ProductDto productDto = ProductDto.builder()
                .id(productId)
                .name(productName)
                .amount(productAmount)
                .category(categoryDto)
                .deleted(false)
                .build();
        ProductEntity productEntity = ProductEntity.builder()
                .id(productId)
                .name(entityName)
                .amount(productAmount)
                .category(categoryEntity)
                .deleted(false)
                .build();

        Mockito.when(productRepository.findByName(entityName)).thenReturn(Optional.of(productEntity));

        //When
        productRightCategoryValidator.isValid(productDto);

        //Then
        assertDoesNotThrow(() -> productRightCategoryValidator.isValid(productDto));
    }

}
