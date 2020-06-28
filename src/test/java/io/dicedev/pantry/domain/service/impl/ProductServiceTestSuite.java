package io.dicedev.pantry.domain.service.impl;

import io.dicedev.pantry.command.entity.ProductEntity;
import io.dicedev.pantry.command.repository.ProductRepository;
import io.dicedev.pantry.domain.dto.ProductDto;
import io.dicedev.pantry.domain.dto.ProductsDto;
import io.dicedev.pantry.domain.enums.ProductCategoryEnum;
import io.dicedev.pantry.domain.service.ProductService;
import io.dicedev.pantry.domain.validate.ProductValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTestSuite {

    ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private List<ProductValidator> productValidator;

    @BeforeEach
    private void setUp() {
        productService = new ProductServiceImpl(productRepository, productValidator);
    }

    @Test
    public void shouldGetOneProductAfterAddingOneProduct() {
        //Given
        ProductDto productDto = new ProductDto();

        //When
        productService.addProduct(productDto);

        //Then
        Mockito.verify(productRepository, Mockito.times(1)).save(any());
    }

    @Test
    public void shouldGetAllProducts() {
        //Given
        Integer entityAmount = 1;
        String entityName = "Product1";
        UUID entityId = UUID.randomUUID();
        List<ProductEntity> allProducts = List.of(ProductEntity.builder()
                .amount(entityAmount)
                .name(entityName)
                .id(entityId)
                .build());
        Mockito.when(productRepository.findByDeleted()).thenReturn(allProducts);

        //When
        ProductsDto productsDto = productService.getProducts();

        //Then
        assertAll(
                () -> assertEquals(entityAmount, productsDto.getProductsDto().get(0).getAmount()),
                () -> assertEquals(entityName, productsDto.getProductsDto().get(0).getName()),
                () -> assertEquals(entityId, productsDto.getProductsDto().get(0).getId())
        );
    }

    @Test
    public void shouldGetEmptyProductListWhenNoProductAdded() {
        //Given

        //When
        ProductsDto result = productService.getProducts();

        //Then
        assertEquals(0, result.getProductsDto().size());
    }

    @Test
    public void shouldGetFiveProductsAfterAddingFiveProducts() {
        //Given
        ProductDto productDto1 = new ProductDto();
        ProductDto productDto2 = new ProductDto();
        ProductDto productDto3 = new ProductDto();
        ProductDto productDto4 = new ProductDto();
        ProductDto productDto5 = new ProductDto();

        //When
        productService.addProduct(productDto1);
        productService.addProduct(productDto2);
        productService.addProduct(productDto3);
        productService.addProduct(productDto4);
        productService.addProduct(productDto5);

        //Then
        Mockito.verify(productRepository, Mockito.times(5)).save(any());
    }

    @Test
    public void shouldIncreaseAmountOfProductIfAddedWithTheSameName() {
        //Given
        Integer entityAmount = 1;
        String entityName = "Product";
        UUID entityId = UUID.randomUUID();
        UUID categoryId = ProductCategoryEnum.FRUITS_AND_VEGETABLES.getId();
        ProductEntity productEntity1 = ProductEntity.builder()
                .amount(entityAmount)
                .name(entityName)
                .id(entityId)
                .deleted(false)
                .build();
        ProductDto productDto1 = new ProductDto(entityId, entityName, entityAmount, categoryId, false);

        Mockito.when(productRepository.findByName(entityName)).thenReturn(productEntity1);

        //When
        productService.addProduct(productDto1);

        //Then
        Mockito.verify(productRepository, Mockito.times(1)).save(productEntity1);
        assertEquals(2, productEntity1.getAmount());
    }

    @Test
    public void shouldRenameOneExistingProduct() {
        //Given
        ProductDto productDto = new ProductDto();
        productService.addProduct(productDto);

        //When
        productService.renameProduct(productDto);

        //Then
        Mockito.verify(productRepository, Mockito.times(1)).findById(productDto.getId());
        Mockito.verify(productRepository, Mockito.times(1)).save(any());
    }

    @Test
    public void shouldNotRenameNotExistingProduct() {
        //Given
        ProductDto productDto = new ProductDto();

        //When
        productService.renameProduct(productDto);

        //Then
        Mockito.verify(productRepository, Mockito.times(1)).findById(productDto.getId());
        Mockito.verify(productRepository, Mockito.times(0)).save(any());
    }

    @Test
    public void shouldAdd10ProductsToProductWhichIsAlreadyInDatabase() {
        //Given
        Integer entityAmount = 10;
        String entityName = "Product";
        UUID entityId = UUID.randomUUID();
        ProductDto productDto = ProductDto.builder()
                .amount(entityAmount)
                .name(entityName)
                .id(entityId)
                .build();

        ProductEntity productEntity = ProductEntity.builder()
                .amount(entityAmount)
                .name(entityName)
                .id(entityId)
                .build();

        ProductEntity expectedProductEntity = ProductEntity.builder()
                .amount(20)
                .name(entityName)
                .id(entityId)
                .build();

        Mockito.when(productRepository.findByName(entityName)).thenReturn(productEntity);

        //When
        productService.addProduct(productDto);

        //Then
        Mockito.verify(productRepository, Mockito.times(1)).save(expectedProductEntity);
    }

    @Test
    public void shouldRemoveProduct() {
        //Given
        UUID productId = UUID.randomUUID();
        ProductDto productDto = new ProductDto();
        productDto.setId(productId);
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productId);
        productRepository.save(productEntity);

        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(productEntity));

        //When
        productService.removeProduct(productDto);

        //Then
        assertEquals(true, productEntity.isDeleted());
    }

}
