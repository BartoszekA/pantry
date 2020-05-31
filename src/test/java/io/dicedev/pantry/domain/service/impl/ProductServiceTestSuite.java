package io.dicedev.pantry.domain.service.impl;

import io.dicedev.pantry.command.entity.ProductEntity;
import io.dicedev.pantry.command.repository.ProductRepository;
import io.dicedev.pantry.domain.dto.ProductDto;
import io.dicedev.pantry.domain.dto.ProductsDto;
import io.dicedev.pantry.domain.service.ProductService;
import io.dicedev.pantry.domain.validate.ProductValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.InvalidKeyException;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTestSuite {

    ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductValidator productValidator;

    @BeforeEach
    private void setUp() {
        productService = new ProductServiceImpl(productRepository, productValidator);
    }

    @Test
    public void shouldGetOneProductAfterAddingOneProduct() throws InvalidKeyException {
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
        Mockito.when(productRepository.findAll()).thenReturn(allProducts);

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
        assertNull(result);
    }

    @Test
    public void shouldGetFiveProductsAfterAddingFiveProducts() throws InvalidKeyException {
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
        ProductsDto productsDto = productService.getProducts();

        //Then
        assertEquals(5, productsDto.getProductsDto().size());
    }

    @Test
    public void shouldCheckIfTwoProductsArePresentAfterAddingTwoDifferentNames() throws InvalidKeyException {
        //Given
        ProductDto productDto = new ProductDto();
        productDto.setName("Product1");
        ProductDto productDto2 = new ProductDto();
        productDto.setName("Product2");

        //When
        productService.addProduct(productDto);
        productService.addProduct(productDto2);
        ProductsDto result = productService.getProducts();

        //Then
        assertAll(
                () -> assertEquals(2, result.getProductsDto().size()),
                () -> assertTrue(result.getProductsDto().contains(productDto)),
                () -> assertTrue(result.getProductsDto().contains(productDto2))
        );
    }

    @Test
    public void shouldCheckIfTwoProductsArePresentAfterAddingTwoSameNames() throws InvalidKeyException {
        //Given
        ProductDto productDto = new ProductDto();
        productDto.setName("Product");
        ProductDto productDto2 = new ProductDto();
        productDto.setName("Product");

        //When
        productService.addProduct(productDto);
        productService.addProduct(productDto2);
        ProductsDto result = productService.getProducts();

        //Then
        assertAll(
                () -> assertEquals(2, result.getProductsDto().size()),
                () -> assertTrue(result.getProductsDto().contains(productDto)),
                () -> assertTrue(result.getProductsDto().contains(productDto2))
        );
    }

    @Test
    public void shouldRenameOneExistingProduct() throws InvalidKeyException {
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
    public void shouldNotRenameNotExistingProduct() throws InvalidKeyException {
        //Given
        ProductDto productDto = new ProductDto();

        //When
        productService.renameProduct(productDto);

        //Then
        Mockito.verify(productRepository, Mockito.times(1)).findById(productDto.getId());
        Mockito.verify(productRepository, Mockito.times(0)).save(any());
    }
}
