package io.dicedev.pantry.domain.service.impl;

import io.dicedev.pantry.domain.dto.ProductDto;
import io.dicedev.pantry.domain.dto.ProductsDto;
import io.dicedev.pantry.domain.service.ProductService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTestSuite {

    ProductService productService = new ProductServiceImpl();

    @Test
    public void shouldGetOneProductAfterAddingOneProduct() {
        //Given
        ProductDto productDto = new ProductDto();

        //When
        productService.addProduct(productDto);
        ProductsDto result = productService.getProducts();

        //Then
        assertEquals(1, result.getProductsDto().size());
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
    public void shouldGetFiveProductsAfterAddingFiveProducts() {
        //Given
        ProductDto productDto1 = new ProductDto("product1");
        ProductDto productDto2 = new ProductDto("product2");
        ProductDto productDto3 = new ProductDto("product3");
        ProductDto productDto4 = new ProductDto("product4");
        ProductDto productDto5 = new ProductDto("product5");

        //When
        productService.addProduct(productDto1);
        productService.addProduct(productDto2);
        productService.addProduct(productDto3);
        productService.addProduct(productDto4);
        productService.addProduct(productDto5);
        ProductsDto result = productService.getProducts();

        //Then
        assertEquals(5, result.getProductsDto().size());
    }

    @Test
    public void shouldCheckIfTwoProductsArePresentAfterAddingTwoDifferentNames() {
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
    public void shouldCheckIfTwoProductsArePresentAfterAddingTwoSameNames() {
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
    public void shouldRenameOneExistingProduct() {
        //Given
        ProductDto productDto = new ProductDto("Product");
        productService.addProduct(productDto);

        //When
        productService.renameProduct(productDto.getId(), "New name");
        String result = productDto.getName();

        //Then
        assertEquals("New name", result);
    }
}
