package io.dicedev.pantry.domain.service.impl;

import io.dicedev.pantry.command.entity.CategoryEntity;
import io.dicedev.pantry.command.entity.ProductEntity;
import io.dicedev.pantry.command.repository.ProductRepository;
import io.dicedev.pantry.domain.dto.CategoryDto;
import io.dicedev.pantry.domain.dto.ProductDto;
import io.dicedev.pantry.domain.dto.ProductsDto;
import io.dicedev.pantry.domain.service.ProductService;
import io.dicedev.pantry.domain.validate.ProductValidator;
import io.dicedev.pantry.mapper.ProductMapper;
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
    private ProductMapper productMapper;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private List<ProductValidator> productValidator;

    @BeforeEach
    private void setUp() {
        productService = new ProductServiceImpl(productMapper, productRepository, productValidator);
    }

    @Test
    public void shouldGetOneProductAfterAddingOneProduct() {
        //Given
        Integer productAmount = 1;
        String productName = "Product";
        UUID productId = UUID.randomUUID();
        CategoryDto categoryDto = new CategoryDto();
        ProductDto productDto = ProductDto.builder()
                .id(productId)
                .name(productName)
                .amount(productAmount)
                .category(categoryDto)
                .deleted(false)
                .build();

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
        CategoryDto categoryDto = new CategoryDto();
        CategoryEntity categoryEntity = new CategoryEntity();
        ProductDto productDto = new ProductDto(entityId, entityName, entityAmount, categoryDto, true);
        ProductEntity productEntity = ProductEntity.builder()
                .id(entityId)
                .name(entityName)
                .amount(entityAmount)
                .category(categoryEntity)
                .deleted(false)
                .build();
        List<ProductEntity> allProducts = List.of(productEntity);
        Mockito.when(productRepository.findByDeleted()).thenReturn(allProducts);
        Mockito.when(productMapper.productEntityToProductDto(productEntity)).thenReturn(productDto);

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
    public void shouldIncreaseAmountOfExistingProductAfterAddingProductWithTheSameNameDifferentLetters() {
        //Given
        String expectedName = "Orange";
        String testedName = "OrAnGe";
        UUID id = UUID.randomUUID();
        Integer amount = 1;
        CategoryDto categoryDto = new CategoryDto();
        CategoryEntity categoryEntity = new CategoryEntity();
        ProductDto productDto = ProductDto.builder()
                .id(id)
                .name(testedName)
                .amount(amount)
                .category(categoryDto)
                .deleted(false)
                .build();
        ProductEntity expectedEntity = ProductEntity.builder()
                .id(id)
                .name(expectedName)
                .amount(amount)
                .category(categoryEntity)
                .deleted(false)
                .build();

        ProductEntity expectedProductEntity = ProductEntity.builder()
                .id(id)
                .name(expectedName)
                .amount(2)
                .category(categoryEntity)
                .deleted(false)
                .build();

        Mockito.when(productRepository.findByName(expectedName)).thenReturn(Optional.ofNullable(expectedEntity));


        //When
        productService.addProduct(productDto);

        //Then
        Mockito.verify(productRepository, Mockito.times(1)).save(expectedProductEntity);
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
    public void shouldGetThreeProductsAfterAddingThreeProducts() {
        //Given
        Integer productAmount1 = 1;
        String productName1 = "Product1";
        UUID productId1 = UUID.randomUUID();
        CategoryDto categoryDto1 = new CategoryDto();
        ProductDto productDto1 = ProductDto.builder()
                .id(productId1)
                .name(productName1)
                .amount(productAmount1)
                .category(categoryDto1)
                .deleted(false)
                .build();

        Integer productAmount2 = 1;
        String productName2 = "Product2";
        UUID productId2 = UUID.randomUUID();
        CategoryDto categoryDto2 = new CategoryDto();
        ProductDto productDto2 = ProductDto.builder()
                .id(productId2)
                .name(productName2)
                .amount(productAmount2)
                .category(categoryDto2)
                .deleted(false)
                .build();

        Integer productAmount3 = 1;
        String productName3 = "Product3";
        UUID productId3 = UUID.randomUUID();
        CategoryDto categoryDto3 = new CategoryDto();
        ProductDto productDto3 = ProductDto.builder()
                .id(productId3)
                .name(productName3)
                .amount(productAmount3)
                .category(categoryDto3)
                .deleted(false)
                .build();

        //When
        productService.addProduct(productDto1);
        productService.addProduct(productDto2);
        productService.addProduct(productDto3);

        //Then
        Mockito.verify(productRepository, Mockito.times(3)).save(any());
    }

    @Test
    public void shouldIncreaseAmountOfProductIfAddedWithTheSameName() {
        //Given
        Integer entityAmount = 1;
        String entityName = "Product";
        UUID entityId = UUID.randomUUID();
        CategoryDto categoryDto = new CategoryDto();
        ProductEntity productEntity = ProductEntity.builder()
                .amount(entityAmount)
                .name(entityName)
                .id(entityId)
                .deleted(false)
                .build();
        ProductDto productDto = ProductDto.builder()
                .id(entityId)
                .name(entityName)
                .amount(entityAmount)
                .category(categoryDto)
                .deleted(false)
                .build();
        ProductEntity expectedProductEntity = ProductEntity.builder()
                .amount(entityAmount + 1)
                .name(entityName)
                .id(entityId)
                .deleted(false)
                .build();

        Mockito.when(productRepository.findByName(entityName)).thenReturn(Optional.ofNullable(productEntity));

        //When
        productService.addProduct(productDto);

        //Then
        Mockito.verify(productRepository, Mockito.times(1)).save(expectedProductEntity);
    }

    @Test
    public void shouldRenameOneExistingProduct() {
        //Given
        Integer productAmount = 1;
        String productName = "Product";
        UUID productId = UUID.randomUUID();
        CategoryDto categoryDto = new CategoryDto();
        CategoryEntity categoryEntity = new CategoryEntity();
        ProductDto productDto = ProductDto.builder()
                .id(productId)
                .name(productName)
                .amount(productAmount)
                .category(categoryDto)
                .deleted(false)
                .build();

        ProductEntity expectedEntity = ProductEntity.builder()
                .id(productId)
                .name(productName)
                .amount(productAmount)
                .category(categoryEntity)
                .deleted(false)
                .build();


        productService.addProduct(productDto);
        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(expectedEntity));

        //When
        productService.renameProduct(productDto);

        //Then
        Mockito.verify(productRepository, Mockito.times(1)).findById(productDto.getId());
        Mockito.verify(productRepository, Mockito.times(2)).save(any());
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

        Mockito.when(productRepository.findByName(entityName)).thenReturn(Optional.ofNullable(productEntity));

        //When
        productService.addProduct(productDto);

        //Then
        Mockito.verify(productRepository, Mockito.times(1)).save(expectedProductEntity);
    }

    @Test
    public void shouldRemoveProduct() {
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
                .deleted(false)
                .build();

        ProductEntity expectedProductEntity = ProductEntity.builder()
                .amount(entityAmount)
                .name(entityName)
                .id(entityId)
                .deleted(true)
                .build();

        Mockito.when(productRepository.findById(entityId)).thenReturn(Optional.of(productEntity));

        //When
        productService.removeProduct(productDto);

        //Then
        Mockito.verify(productRepository, Mockito.times(1)).save(expectedProductEntity);
    }
}
